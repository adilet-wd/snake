package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.dto.UpdateParticipantDTO;
import com.example.snake.contest.models.Participant;
import org.springframework.http.ResponseEntity;

public interface ParticipantService {
    ResponseEntity<?> getParticipants(long tournamentId);
    ResponseEntity<?> getParticipant(long tournamentId, long id);
    ResponseEntity<?> saveParticipant(CreateParticipantDTO createParticipantDTO);
    ResponseEntity<?> deleteParticipant(long tournamentId, long id);
    ResponseEntity<?> updateParticipant(UpdateParticipantDTO updateParticipantDTO, long tournamentId, long id);
}
