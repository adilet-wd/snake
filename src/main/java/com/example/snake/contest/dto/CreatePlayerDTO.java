package com.example.snake.contest.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePlayerDTO {

    @NotNull
    private Integer participant_id;

//    Сеттеры геттеры
    public @NotNull Integer getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(@NotNull Integer participant_id) {
        this.participant_id = participant_id;
    }

}
