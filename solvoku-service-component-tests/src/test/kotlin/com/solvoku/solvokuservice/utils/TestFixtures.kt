package com.solvoku.solvokuservice.utils

import com.solvoku.solvokuservice.domain.model.CellPosition
import com.solvoku.solvokuservice.domain.model.Clue
import com.solvoku.solvokuservice.domain.model.GridElement
import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.model.Suspect
import com.solvoku.solvokuservice.domain.model.SuspectPlacement
import com.solvoku.solvokuservice.domain.model.Victim
import com.solvoku.solvokuservice.domain.model.Zone
import com.solvoku.solvokuservice.outbound.db.PuzzlesTable
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.LocalDate
import java.util.UUID

fun buildTestPuzzle(
    id: UUID = UUID.randomUUID(),
    date: LocalDate = LocalDate.now(),
    title: String = "Test Puzzle",
    gridSize: Int = 4,
    zones: List<Zone> = emptyList(),
    elements: List<GridElement> = emptyList(),
    suspects: List<Suspect> = listOf(Suspect("s1", "Colonel Mustard", "mustard-icon")),
    victim: Victim = Victim("Mr. Body", "body-icon", CellPosition(0, 0)),
    clues: List<Clue> = listOf(Clue(1, "The suspect was seen near the library")),
    solution: SuspectPlacement = SuspectPlacement(mapOf("s1" to CellPosition(1, 1))),
    killerId: String = "s1"
) = Puzzle(id, date, title, gridSize, zones, elements, suspects, victim, clues, solution, killerId)

fun insertPuzzle(puzzle: Puzzle) = transaction {
    PuzzlesTable.insert {
        it[id]       = puzzle.id
        it[date]     = puzzle.date
        it[title]    = puzzle.title
        it[gridSize] = puzzle.gridSize
        it[zones]    = puzzle.zones
        it[elements] = puzzle.elements
        it[suspects] = puzzle.suspects
        it[victim]   = puzzle.victim
        it[clues]    = puzzle.clues
        it[solution] = puzzle.solution
        it[killerId] = puzzle.killerId
    }
}
