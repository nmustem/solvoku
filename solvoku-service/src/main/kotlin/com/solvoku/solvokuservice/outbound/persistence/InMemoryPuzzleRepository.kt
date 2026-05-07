package com.solvoku.solvokuservice.outbound.persistence

import com.solvoku.solvokuservice.domain.model.CellPosition
import com.solvoku.solvokuservice.domain.model.Clue
import com.solvoku.solvokuservice.domain.model.GridElement
import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.model.Suspect
import com.solvoku.solvokuservice.domain.model.SuspectPlacement
import com.solvoku.solvokuservice.domain.model.Victim
import com.solvoku.solvokuservice.domain.model.Zone
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import java.time.LocalDate
import java.util.UUID

class InMemoryPuzzleRepository : PuzzleRepository {

    private val puzzles: List<Puzzle> = listOf(hardcodedPuzzle())

    override fun findByDate(date: LocalDate): Puzzle? =
        puzzles.find { it.date == date }

/*
    override fun findById(id: UUID): Puzzle? =
        puzzles.find { it.id == id }

    override fun findAll(page: Int, size: Int): PuzzlePage {
        val items = puzzles.drop(page * size).take(size)
        return PuzzlePage(items, page, size, puzzles.size.toLong())
    }

    override fun save(puzzle: Puzzle): Puzzle {
        throw UnsupportedOperationException("InMemoryPuzzleRepository is read-only")
    }
 */
}


private fun hardcodedPuzzle() = Puzzle(
    id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000"),
    date = LocalDate.now(),
    // theme = Theme.MURDER,
    // difficulty = Difficulty.MEDIUM,
    title = "The Manor Mystery",
    gridSize = 4,
    zones = listOf(
        Zone("A", "Library", listOf(CellPosition(0,0), CellPosition(0,1), CellPosition(1,0), CellPosition(1,1))),
        Zone("B", "Kitchen", listOf(CellPosition(0,2), CellPosition(0,3), CellPosition(1,2), CellPosition(1,3))),
        Zone("C", "Study", listOf(CellPosition(2, 0), CellPosition(2, 1), CellPosition(3, 0), CellPosition(3, 1))),
        Zone("D", "Garden",  listOf(CellPosition(2,2), CellPosition(2,3), CellPosition(3,2), CellPosition(3,3)))
    ),
    elements = listOf(
        GridElement(CellPosition(0,0), "bookcase", "Bookcase"),
        GridElement(CellPosition(0,3), "knife",    "Knife"),
        GridElement(CellPosition(1,2), "stove",    "Stove"),
        GridElement(CellPosition(2,1), "candle",   "Candle"),
        GridElement(CellPosition(3,0), "chair",    "Chair"),
        GridElement(CellPosition(3, 3), "rose", "Rose")
    ),
    suspects = listOf(
        Suspect("A", "Anna",   "suspect_woman_1"),
        Suspect("B", "Boris", "suspect_man_1"),
        Suspect("C", "Clara",  "suspect_woman_2"),
        Suspect("D", "Duncan", "suspect_man_2")
    ),
    victim = Victim("Lord Ashton", "skull", CellPosition(2, 3)),
    clues = listOf(
        Clue(1, "Anna was in the Kitchen."),
        Clue(2, "Boris was not in the Garden."),
        Clue(3, "Clara was beside the bookcase."),
        Clue(4, "Duncan was in the same zone as the victim."),
        Clue(5, "No suspect shared a row with Anna."),
        Clue(6, "Boris was in the Study.")
    ),
    solution = SuspectPlacement(
        mapOf(
            "A" to CellPosition(0, 2),
            "B" to CellPosition(3, 1),
            "C" to CellPosition(1, 0),
            "D" to CellPosition(2, 3)
        )
    ),
    killerId = "D"
)