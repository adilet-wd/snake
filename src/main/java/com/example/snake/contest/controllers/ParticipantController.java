package com.example.snake.contest.controllers;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.dto.UpdateParticipantDTO;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.services.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "participant", description = "the participant API")
@RestController
@RequestMapping("/api/")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping("participant/")
    @Operation(summary = "Take part in tournament", description = "Участовать на турнире", tags = { "participant" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Participant.class))
                    })})
    public ResponseEntity<?> saveParticipant(@Valid @RequestBody CreateParticipantDTO createParticipantDTO) {
        return participantService.saveParticipant(createParticipantDTO);
    }

    @GetMapping("participant/{tournamentId}/")
    @Operation(summary = "Get all participants of tournament", description = "Получение всех участников турнира", tags = { "participant" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Participant.class))
                    })})
    public ResponseEntity<?> getParticipants(@PathVariable long tournamentId) {
        return participantService.getParticipants(tournamentId);
    }

    @GetMapping("participant/{tournamentId}/{id}/")
    @Operation(summary = "Participants of tournament", description = "Получение участника турнира", tags = { "participant" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Participant.class))
                    })})
    public ResponseEntity<?> getParticipant(@PathVariable long tournamentId, @PathVariable long id) {
        return participantService.getParticipant(tournamentId, id);
    }

    @DeleteMapping("participant/{tournamentId}/{id}/")
    @Operation(summary = "Delete participant", description = "Удаление участника", tags = { "participant" })
    public ResponseEntity<?> deleteParticipant(@PathVariable long tournamentId, @PathVariable long id) {
        return participantService.deleteParticipant(tournamentId, id);
    }

    @PatchMapping("participant/{tournamentId}/{id}/")
    @Operation(summary = "Edit participant", description = "Изменение участника", tags = { "participant" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Participant.class))
                    })})
    public ResponseEntity<?> updateParticipant(@Valid @RequestBody UpdateParticipantDTO updateParticipantDTO, @PathVariable long tournamentId, @PathVariable long id) {
        return participantService.updateParticipant(updateParticipantDTO, tournamentId, id);
    }

}
