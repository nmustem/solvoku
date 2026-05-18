package com.solvoku.solvokuservice.domain.model

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.UUID

@Serializable
data class Puzzle(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    // val theme: Theme,
    // val difficulty: Difficulty,
    val title: String,
    val gridSize: Int,
    val zones: List<Zone>,
    val elements: List<GridElement>,
    val suspects: List<Suspect>,
    val victim: Victim,
    val clues: List<Clue>,
    val solution: SuspectPlacement,  // never leaves the domain. This field is stripped in the DTO before the HTTP response, so the client never sees the answer
    val killerId: String             // never leaves the domain
) {
    //fun zoneOf(cell: CellPosition): Zone? = zones.find { it.contains(cell) }

    //fun suspectById(id: String): Suspect? = suspects.find { it.id == id }

    //fun isKiller(suspectId: String): Boolean = suspectId == killerId
}