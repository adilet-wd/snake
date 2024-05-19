package com.example.snake.contest.controllers;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.models.Match;
import com.example.snake.contest.services.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/matches")
    public Match save(@Valid @RequestBody CreateMatchDTO createMatchDTO) {
        return matchService.saveMatch(createMatchDTO);
    }

    @GetMapping("/matches")
    public List<Match> get() {
        return matchService.getAllMatches();
    }
}
