package com.example.snake.contest.dto;


import jakarta.validation.constraints.NotNull;

import java.sql.Date;


public class CreateTournamentDTO {
    @NotNull
    private String name;

    @NotNull
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
