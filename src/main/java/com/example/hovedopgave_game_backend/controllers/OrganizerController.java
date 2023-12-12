package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Organizer;
import com.example.hovedopgave_game_backend.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("organizer/organizers")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(organizerService.findAll());
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity getByID(@PathVariable("tokenId") String tokenId){
        Optional<Organizer> organizer = organizerService.findByTokenId(tokenId);
        if (organizer.isPresent()) {
            return new ResponseEntity<>(organizer.get().getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Organizer found with id " + tokenId, HttpStatus.NOT_FOUND);
        }
    }
}
