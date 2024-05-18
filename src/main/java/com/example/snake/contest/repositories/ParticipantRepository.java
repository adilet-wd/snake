package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}
