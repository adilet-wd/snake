package com.example.snake.contest.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePlayerDTO {

    @NotNull
    private Long participant_id;

//    Сеттеры геттеры
    public @NotNull Long getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(@NotNull Long participant_id) {
        this.participant_id = participant_id;
    }

}
