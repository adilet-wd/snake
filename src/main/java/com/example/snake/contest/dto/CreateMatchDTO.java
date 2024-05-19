package com.example.snake.contest.dto;

import jakarta.validation.constraints.NotNull;

public class CreateMatchDTO {
    @NotNull
    private Long tournament_id;

    @NotNull
    private Long participant1_id;

    @NotNull
    private Long participant2_id;


    //    Сеттеры и геттеры

    public @NotNull Long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(@NotNull Long tournament_id) {
        this.tournament_id = tournament_id;
    }

    public @NotNull Long getParticipant1_id() {
        return participant1_id;
    }

    public void setParticipant1_id(@NotNull Long participant1_id) {
        this.participant1_id = participant1_id;
    }

    public @NotNull Long getParticipant2_id() {
        return participant2_id;
    }

    public void setParticipant2_id(@NotNull Long participant2_id) {
        this.participant2_id = participant2_id;
    }
}
