package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;

import java.util.List;

public interface TournamentService {
    List<Tournament> getTournaments();
    Tournament getTournament(long id);
    void saveTournament(CreateTournamentDTO createTournamentDTO);
    void deleteTournament(long id);
    void updateTournament(Tournament tournament);
}
