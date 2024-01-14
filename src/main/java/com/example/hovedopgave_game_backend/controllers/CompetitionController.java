package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Competition;
import com.example.hovedopgave_game_backend.services.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("competitions")
public class CompetitionController {
    private CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(this.competitionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Competition> competition = competitionService.findById(id);
        if (competition.isPresent()) {
            return new ResponseEntity<>(competition.get(), HttpStatus.OK);
        } else {
            System.out.println("No competition found with id: " + id);
            return new ResponseEntity<>("No Competition found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Competition competition){
        Map<String, String> message = new HashMap<>();
        if (competition != null){
            competitionService.save(competition);
            message.put("Message", "Competition is created " + competition );
        } else {
            message.put("Message", "Failed to create Competition: " + competition);
        }
        return ResponseEntity.ok(message);
    }


}
