package com.example.snake.contest.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;


public class CreateTournamentDTO {

    @NotNull(message = "Name can't be null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Date is required")
    @Future(message = "Date should be valid, year-day-month")
    private Date date;

    @NotNull(message = "Description is required")
    @Size(min = 2, max = 200, message = "Description should be between 2 and 100 characters")
    private String description;

    //    Сеттеры геттеры


    public @NotNull(message = "Name can't be null") @Size(min = 2, max = 30, message = "Name should be between 2 and 50 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name can't be null") @Size(min = 2, max = 30, message = "Name should be between 2 and 50 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Date is required") @Future(message = "Date should be valid, year-day-month") Date getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Date is required") @Future(message = "Date should be valid, year-day-month") Date date) {
        this.date = date;
    }

    public @NotNull(message = "Description is required") @Size(min = 2, max = 200, message = "Description should be between 2 and 100 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "Description is required") @Size(min = 2, max = 200, message = "Description should be between 2 and 100 characters") String description) {
        this.description = description;
    }
}
