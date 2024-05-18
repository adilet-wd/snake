package com.example.snake.contest.dto;

import com.example.snake.contest.models.Tournament;
import com.example.snake.security.models.CustomUser;
import jakarta.validation.constraints.NotNull;

public class CreateParticipantDTO {

    @NotNull
    private String function;

    @NotNull
    private Long tournament_id;

    @NotNull
    private Long user_id;


    //    Сеттеры геттеры
    public @NotNull String getFunction() {
        return function;
    }

    public void setFunction(@NotNull String function) {
        this.function = function;
    }

    public @NotNull Long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(@NotNull Long tournament_id) {
        this.tournament_id = tournament_id;
    }

    public @NotNull Long getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull Long user_id) {
        this.user_id = user_id;
    }
}
