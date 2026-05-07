package com.solvoku.solvokuservice.domain.model

data class Zone(
    val id: String,
    val name: String,
    val cells: List<CellPosition>
) {
    fun contains(cell: CellPosition): Boolean = cells.contains(cell)
}

data class GridElement(
    val cell: CellPosition,
    val icon: String, // icon/object/element
    val label: String
)

data class Suspect(
    val id: String,
    val name: String,
    val icon: String
)

data class Victim(
    val name: String,
    val icon: String,
    val cell: CellPosition
)

data class Clue(
    val id: Int,
    val text: String
)

data class SuspectPlacement(val entries: Map<String, CellPosition>) {
    fun cellFor(suspectId: String): CellPosition? = entries[suspectId]
}
