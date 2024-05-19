package com.example.snake.contest.controllers;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.services.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/participant")
    public List<Participant> get() {
        return participantService.getParticipants();
    }

    @PostMapping("/participant")
    public Participant save(@Valid @RequestBody CreateParticipantDTO createParticipantDTO) {
        return participantService.saveParticipant(createParticipantDTO);
    }

}
