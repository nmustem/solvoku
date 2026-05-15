package com.solvoku.solvokuservice.outbound.db

import com.solvoku.solvokuservice.domain.model.Clue
import com.solvoku.solvokuservice.domain.model.GridElement
import com.solvoku.solvokuservice.domain.model.Suspect
import com.solvoku.solvokuservice.domain.model.SuspectPlacement
import com.solvoku.solvokuservice.domain.model.Victim
import com.solvoku.solvokuservice.domain.model.Zone
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.java.javaUUID
import org.jetbrains.exposed.v1.javatime.date
import org.jetbrains.exposed.v1.json.jsonb


object PuzzlesTable : Table("puzzles") {
    val id         = javaUUID("id")
    val date       = date("date").uniqueIndex()
    // val theme      = enumerationByName<Theme>("theme", 50)
    // val difficulty = enumerationByName<Difficulty>("difficulty", 50)
    val title      = varchar("title", 255)
    val gridSize   = integer("grid_size")
    val zones      = jsonb<List<Zone>>("zones", Json.Default)
    val elements   = jsonb<List<GridElement>>("elements", Json.Default)
    val suspects   = jsonb<List<Suspect>>("suspects", Json.Default)
    val victim     = jsonb<Victim>("victim", Json.Default)
    val clues      = jsonb<List<Clue>>("clues", Json.Default)
    val solution   = jsonb<SuspectPlacement>("solution", Json.Default)
    val killerId   = varchar("killer_id", 100)

    override val primaryKey = PrimaryKey(id)
}
