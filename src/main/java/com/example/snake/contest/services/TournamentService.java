package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TournamentService {
    List<Tournament> getTournaments();
    ResponseEntity<?> getTournamentById(long id);
    ResponseEntity<?> getTournamentByName(String name);
    ResponseEntity<?> saveTournament(CreateTournamentDTO createTournamentDTO);
    ResponseEntity<?> deleteTournament(long id);
}
