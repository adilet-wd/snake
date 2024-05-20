package com.example.snake.contest.controllers;


import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.services.TournamentService;
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

@Tag(name = "tournament", description = "the tournament API")
@RestController
@RequestMapping("/api/")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("tournament/")
    @Operation(summary = "Create tournament", description = "Создание турнира, доступно только для ADMIN", tags = { "tournament" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tournament.class))
                    })})
    public ResponseEntity<?> save(@Valid @RequestBody CreateTournamentDTO tournamentDTO) {
        return tournamentService.saveTournament(tournamentDTO);
    }


    @GetMapping("tournament/")
    @Operation(summary = "Get all tournaments", description = "Получение всех турниров", tags = { "tournament" })
    public List<Tournament> get() {
        return tournamentService.getTournaments();
    }

    @GetMapping("tournament/{id}/")
    @Operation(summary = "Get tournament by id", description = "Получение турнира по id, , доступно только для ADMIN" , tags = { "tournament" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tournament.class))
                    })})
    public ResponseEntity<?> getTournamentById(@PathVariable long id) {
        return tournamentService.getTournamentById(id);
    }

    @GetMapping("tournament")
    @Operation(summary = "Get tournament by name", description = "Получение турнира по name" , tags = { "tournament" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Tournament.class))
                    })})
    public ResponseEntity<?> getTournamentByName(@RequestParam String name) {
        return tournamentService.getTournamentByName(name);
    }

    @DeleteMapping ("tournament/{id}/")
    @Operation(summary = "Delete tournament by id", description = "Удаление турнира по id, доступно только для ADMIN", tags = { "tournament" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return tournamentService.deleteTournament(id);
    }
}
