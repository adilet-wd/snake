package com.example.snake.contest.dto;

import jakarta.validation.constraints.NotNull;

public class CreateMatchDTO {
    @NotNull(message = "Tournament id can't be null")
    private Long tournament_id;

    @NotNull(message = "Participant 1 id can't be null")
    private Integer participant1_id;

    @NotNull(message = "Participant 2 id can't be null")
    private Integer participant2_id;


    //    Сеттеры и геттеры

    public @NotNull(message = "Tournament id can't be null") Long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(@NotNull(message = "Tournament id can't be null") Long tournament_id) {
        this.tournament_id = tournament_id;
    }

    public @NotNull(message = "Participant 1 id can't be null") Integer getParticipant1_id() {
        return participant1_id;
    }

    public void setParticipant1_id(@NotNull(message = "Participant 1 id can't be null") Integer participant1_id) {
        this.participant1_id = participant1_id;
    }

    public @NotNull(message = "Participant 2 id can't be null") Integer getParticipant2_id() {
        return participant2_id;
    }

    public void setParticipant2_id(@NotNull(message = "Participant 2 id can't be null") Integer participant2_id) {
        this.participant2_id = participant2_id;
    }
}
