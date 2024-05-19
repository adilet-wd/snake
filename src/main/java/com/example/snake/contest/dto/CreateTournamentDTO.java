package com.example.snake.contest.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;


public class CreateTournamentDTO {

    @NotNull
    @Size(min = 2, max = 30, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull
    @Future(message = "Date should be valid, year-day-month")
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
