package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    Optional<Participant> findByCustomUserIdAndTournamentId(Long customUserId, Long tournamentId);
}
