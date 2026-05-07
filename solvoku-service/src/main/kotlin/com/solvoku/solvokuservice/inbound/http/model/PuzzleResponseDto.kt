package com.solvoku.solvokuservice.inbound.http.model

import kotlinx.serialization.Serializable

@Serializable
data class CellPositionDto(val row: Int, val col: Int)

@Serializable
data class ZoneDto(val id: String, val name: String, val cells: List<CellPositionDto>)

@Serializable
data class GridElementDto(val cell: CellPositionDto, val icon: String, val label: String)

@Serializable
data class SuspectDto(val id: String, val name: String, val icon: String)

@Serializable
data class VictimDto(val name: String, val icon: String, val cell: CellPositionDto)

@Serializable
data class ClueDto(val id: Int, val text: String)

@Serializable
data class PuzzleResponseDto(
    val id: String,
    val date: String,
    // val theme: String,
    // val difficulty: String,
    val title: String,
    val gridSize: Int,
    val zones: List<ZoneDto>,
    val elements: List<GridElementDto>,
    val suspects: List<SuspectDto>,
    val victim: VictimDto,
    val clues: List<ClueDto>
    // solution and killerId deliberately absent
)
