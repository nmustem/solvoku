package com.solvoku.solvokuservice.application

import com.solvoku.solvokuservice.domain.model.PuzzleNotFoundException
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleCachePort
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import com.solvoku.solvokuservice.fixtures.buildFakePuzzle
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate

class GetTodaysPuzzleUseCaseTest : FreeSpec({

    val cache: PuzzleCachePort = mockk()
    val repository: PuzzleRepository = mockk()
    val systemUnderTest = GetTodaysPuzzleUseCaseImpl(cache, repository)

    val today = LocalDate.now()
    val fakePuzzle = buildFakePuzzle(today)

    "returns puzzle from cache when available" {
        every { cache.getTodaysPuzzle() } returns fakePuzzle

        val result = systemUnderTest()

        assertEquals(fakePuzzle.id, result.id)
    }

    "falls back to repository when cache is empty" {
        every { cache.getTodaysPuzzle() } returns null
        every { repository.findByDate(today) } returns fakePuzzle

        val result = systemUnderTest()

        assertEquals(fakePuzzle.id, result.id)
    }

    "throws PuzzleNotFoundException when no puzzle exists for today" {
        every { cache.getTodaysPuzzle() } returns null
        every { repository.findByDate(today) } returns null

        val exception = shouldThrow<PuzzleNotFoundException> {
            systemUnderTest()
        }

        exception.message shouldBe "No puzzle scheduled for today"
    }
})
