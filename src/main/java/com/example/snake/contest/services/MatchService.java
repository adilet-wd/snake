package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.models.Match;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchService {
    ResponseEntity<?> getMatches(Long tournamentId);
    ResponseEntity<?> getMatch(long tournamentId, long id);
    ResponseEntity<?> saveMatch(CreateMatchDTO createMatchDTO);
    ResponseEntity<?>  deleteMatch(long tournamentId, long id);
    void updateMatch(Match match);
}
