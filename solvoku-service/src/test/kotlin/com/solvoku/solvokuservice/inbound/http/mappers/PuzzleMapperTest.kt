package com.solvoku.solvokuservice.inbound.http.mappers

import com.solvoku.solvokuservice.domain.model.CellPosition
import com.solvoku.solvokuservice.domain.model.Clue
import com.solvoku.solvokuservice.domain.model.GridElement
import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.model.Suspect
import com.solvoku.solvokuservice.domain.model.SuspectPlacement
import com.solvoku.solvokuservice.domain.model.Victim
import com.solvoku.solvokuservice.domain.model.Zone
import com.solvoku.solvokuservice.inbound.http.model.CellPositionDto
import com.solvoku.solvokuservice.inbound.http.model.ClueDto
import com.solvoku.solvokuservice.inbound.http.model.GridElementDto
import com.solvoku.solvokuservice.inbound.http.model.SuspectDto
import com.solvoku.solvokuservice.inbound.http.model.VictimDto
import com.solvoku.solvokuservice.inbound.http.model.ZoneDto
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.util.UUID

class PuzzleMapperTest : FreeSpec({

    "toResponseDto" - {
        "maps all fields correctly" {
            val puzzleId = UUID.fromString("00000000-0000-0000-0000-000000000001")
            val date = LocalDate.of(2026, 5, 18)
            val puzzle = Puzzle(
                id = puzzleId,
                date = date,
                title = "The Library Mystery",
                gridSize = 4,
                zones = listOf(Zone("z1", "Library", listOf(CellPosition(0, 0), CellPosition(0, 1)))),
                elements = listOf(GridElement(CellPosition(1, 1), "candlestick", "Candlestick")),
                suspects = listOf(Suspect("s1", "Colonel Mustard", "mustard-icon")),
                victim = Victim("Mr. Body", "body-icon", CellPosition(2, 2)),
                clues = listOf(Clue(1, "The suspect was seen near the library")),
                solution = SuspectPlacement(mapOf("s1" to CellPosition(0, 0))),
                killerId = "s1"
            )

            val dto = puzzle.toResponseDto()

            dto.id shouldBe "00000000-0000-0000-0000-000000000001"
            dto.date shouldBe "2026-05-18"
            dto.title shouldBe "The Library Mystery"
            dto.gridSize shouldBe 4
            dto.zones shouldBe listOf(ZoneDto("z1", "Library", listOf(CellPositionDto(0, 0), CellPositionDto(0, 1))))
            dto.elements shouldBe listOf(GridElementDto(CellPositionDto(1, 1), "candlestick", "Candlestick"))
            dto.suspects shouldBe listOf(SuspectDto("s1", "Colonel Mustard", "mustard-icon"))
            dto.victim shouldBe VictimDto("Mr. Body", "body-icon", CellPositionDto(2, 2))
            dto.clues shouldBe listOf(ClueDto(1, "The suspect was seen near the library"))
        }

        "does not expose solution or killerId" {
            val puzzle = Puzzle(
                id       = UUID.randomUUID(),
                date     = LocalDate.now(),
                title    = "Test",
                gridSize = 4,
                zones    = emptyList(),
                elements = emptyList(),
                suspects = emptyList(),
                victim   = Victim("V", "icon", CellPosition(0, 0)),
                clues    = emptyList(),
                solution = SuspectPlacement(mapOf("s1" to CellPosition(3, 3))),
                killerId = "s1"
            )

            val dto = puzzle.toResponseDto()

            dto::class.members.map { it.name }.let { fields ->
                fields.contains("solution") shouldBe false
                fields.contains("killerId") shouldBe false
            }
        }
    }
})
