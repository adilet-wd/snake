package com.example.snake.contest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateParticipantDTO {
    @Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters")
    @NotNull(message = "function is required")
    private String function;

    //    Сеттеры геттеры
    public @Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters") @NotNull(message = "function is required") String getFunction() {
        return function;
    }

    public void setFunction(@Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters") @NotNull(message = "function is required") String function) {
        this.function = function;
    }
}
