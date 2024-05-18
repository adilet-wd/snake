package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.models.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipants();
    Participant getParticipant(long id);
    Participant saveParticipant(CreateParticipantDTO createParticipantDTO);
    void deleteParticipant(long id);
    void updateParticipant(Participant participant);
}
