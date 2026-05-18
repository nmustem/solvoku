package com.solvoku.solvokuservice.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Zone(
    val id: String,
    val name: String,
    val cells: List<CellPosition>
) {
    fun contains(cell: CellPosition): Boolean = cells.contains(cell)
}

@Serializable
data class GridElement(
    val cell: CellPosition,
    val icon: String, // icon/object/element
    val label: String
)

@Serializable
data class Suspect(
    val id: String,
    val name: String,
    val icon: String
)

@Serializable
data class Victim(
    val name: String,
    val icon: String,
    val cell: CellPosition
)

@Serializable
data class Clue(
    val id: Int,
    val text: String
)

@Serializable
data class SuspectPlacement(val entries: Map<String, CellPosition>) {
    fun cellFor(suspectId: String): CellPosition? = entries[suspectId]
}
