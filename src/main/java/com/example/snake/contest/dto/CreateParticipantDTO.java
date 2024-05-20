package com.example.snake.contest.dto;

import com.example.snake.contest.models.Tournament;
import com.example.snake.security.models.CustomUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateParticipantDTO {

    @Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters")
    @NotNull(message = "function is required")
    private String function;

    @NotNull(message = "Tournament id can't be null")@NotNull
    private Long tournament_id;

    @NotNull(message = "User id can't be null")
    private Long user_id;


    //    Сеттеры геттеры


    public @Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters") @NotNull(message = "function is required") String getFunction() {
        return function;
    }

    public void setFunction(@Size(min = 2, max = 1000, message = "function should be between 2 and 1000 characters") @NotNull(message = "function is required") String function) {
        this.function = function;
    }

    public @NotNull(message = "Tournament id can't be null") @NotNull Long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(@NotNull(message = "Tournament id can't be null") @NotNull Long tournament_id) {
        this.tournament_id = tournament_id;
    }

    public @NotNull(message = "User id can't be null") Long getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull(message = "User id can't be null") Long user_id) {
        this.user_id = user_id;
    }
}
