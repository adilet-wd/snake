package com.example.snake.contest.controllers;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.models.Match;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.services.MatchService;
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

@Tag(name = "match", description = "the match API")
@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("matches/")
    @Operation(summary = "Create match in tournament", description = "Создание матча, доступно только для ADMIN", tags = { "match" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))
                    })})
    public ResponseEntity<?> save(@Valid @RequestBody CreateMatchDTO createMatchDTO) {
        return matchService.saveMatch(createMatchDTO);
    }

    @GetMapping("/matches/{tournamentId}/")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))
                    })})
    @Operation(summary = "Get all matches of tournament", description = "Получение всех матчей турнира", tags = { "match" })
    public ResponseEntity<?> getMatches(@PathVariable long tournamentId) {
        return matchService.getMatches(tournamentId);
    }

    @GetMapping("matches/{tournamentId}/{id}/")
    @Operation(summary = "Match of tournament", description = "Получение матча турнира", tags = { "match" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Match.class))
                    })})
    public ResponseEntity<?> getMatch(@PathVariable long tournamentId, @PathVariable long id) {
        return matchService.getMatch(tournamentId, id);
    }

//    @DeleteMapping("matches/{tournamentId}/{id}/")
//    @Operation(summary = "Delete match", description = "Удаление матча", tags = { "match" })
//    public ResponseEntity<?> deleteMatch(@PathVariable long tournamentId, @PathVariable long id) {
//        return matchService.deleteMatch(tournamentId, id);
//    }
}
