package com.solvoku.solvokuservice.inbound.http.mappers

import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.inbound.http.model.CellPositionDto
import com.solvoku.solvokuservice.inbound.http.model.ClueDto
import com.solvoku.solvokuservice.inbound.http.model.GridElementDto
import com.solvoku.solvokuservice.inbound.http.model.PuzzleResponseDto
import com.solvoku.solvokuservice.inbound.http.model.SuspectDto
import com.solvoku.solvokuservice.inbound.http.model.VictimDto
import com.solvoku.solvokuservice.inbound.http.model.ZoneDto


fun Puzzle.toResponseDto() = PuzzleResponseDto(
    id = id.toString(),
    date = date.toString(),
    // theme = theme.name,
    // difficulty = difficulty.name,
    title = title,
    gridSize = gridSize,
    zones = zones.map { z ->
        ZoneDto(z.id, z.name, z.cells.map { CellPositionDto(it.row, it.col) })
    },
    elements = elements.map { GridElementDto(CellPositionDto(it.cell.row, it.cell.col), it.icon, it.label) },
    suspects = suspects.map { SuspectDto(it.id, it.name, it.icon) },
    victim = VictimDto(victim.name, victim.icon, CellPositionDto(victim.cell.row, victim.cell.col)),
    clues = clues.map { ClueDto(it.id, it.text) }
)
