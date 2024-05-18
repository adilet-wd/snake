package com.example.snake.contest.controllers;


import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/tournament")
    public List<Tournament> get() {
        return tournamentService.getTournaments();
    }

    @PostMapping("/tournament")
    public String save(@Valid @RequestBody CreateTournamentDTO tournamentDTO) {
        tournamentService.saveTournament(tournamentDTO);
        return "Tournament created";
    }


}
