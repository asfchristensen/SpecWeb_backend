package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Competition;
import com.example.hovedopgave_game_backend.models.Organizer;
import com.example.hovedopgave_game_backend.services.CompetitionService;
import com.example.hovedopgave_game_backend.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private OrganizerService organizerService;

    @GetMapping("/organizer/competitions")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(this.competitionService.findAll());
    }

    @GetMapping("/spectator/competitions/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Competition> competition = competitionService.findById(id);
        if (competition.isPresent()) {
            return new ResponseEntity<>(competition.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Competition found with id " + id, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/organizer/competitions/{tokenId}")
    public ResponseEntity getByTokenID(@PathVariable("tokenId") String tokenId){

        Optional<Organizer> organizer = organizerService.findByTokenId(tokenId);
        if (organizer.isPresent()) {
            long organizerId = organizer.get().getId();
            List<Competition> competitions = competitionService.getAllByOrganizer_Id(organizerId);
            return new ResponseEntity<>(competitions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Organizer found with token id " + tokenId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/organizer/competitions")
    public ResponseEntity create(@RequestBody Competition competition){
        if (competition != null){
            competitionService.save(competition);
            return new ResponseEntity(competition, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Failed to create competition " + competition, HttpStatus.BAD_REQUEST);
        }
    }
}
