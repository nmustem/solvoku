package com.solvoku.solvokuservice.domain.model

import java.time.LocalDate

class PuzzleNotFoundException(today: LocalDate) : RuntimeException("No puzzle scheduled for $today")
// class UnauthorizedException(message: String) : RuntimeException(message)
