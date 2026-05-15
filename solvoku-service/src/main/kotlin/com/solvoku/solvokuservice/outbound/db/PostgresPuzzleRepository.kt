package com.solvoku.solvokuservice.outbound.db

import org.jetbrains.exposed.v1.core.eq
import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.LocalDate

class PostgresPuzzleRepository : PuzzleRepository {
    override fun findByDate(date: LocalDate): Puzzle? = transaction {
        PuzzlesTable
            .selectAll()
            .where { PuzzlesTable.date eq date }
            .singleOrNull()
            ?.let { row -> // TODO: we could use mappers for readability once this file grows
                Puzzle(
                    id          = row[PuzzlesTable.id],
                    date        = row[PuzzlesTable.date],
                    // theme       = Theme.valueOf(row[PuzzlesTable.theme]),
                    // difficulty  = Difficulty.valueOf(row[PuzzlesTable.difficulty]),
                    title       = row[PuzzlesTable.title],
                    gridSize    = row[PuzzlesTable.gridSize],
                    zones       = row[PuzzlesTable.zones],
                    elements    = row[PuzzlesTable.elements],
                    suspects    = row[PuzzlesTable.suspects],
                    victim      = row[PuzzlesTable.victim],
                    clues       = row[PuzzlesTable.clues],
                    solution    = row[PuzzlesTable.solution],
                    killerId    = row[PuzzlesTable.killerId]
                )
            }
    }
}
