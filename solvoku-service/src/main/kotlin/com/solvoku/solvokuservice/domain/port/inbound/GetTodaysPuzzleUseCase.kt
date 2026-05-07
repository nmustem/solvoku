package com.solvoku.solvokuservice.domain.port.inbound

import com.solvoku.solvokuservice.domain.model.Puzzle

public interface GetTodaysPuzzleUseCase {
    public abstract operator fun invoke(): Puzzle
}
