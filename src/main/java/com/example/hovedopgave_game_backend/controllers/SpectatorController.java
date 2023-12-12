package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Organizer;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.services.SpectatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("spectator/spectators")
public class SpectatorController {
    @Autowired
    private SpectatorService spectatorService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(spectatorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Spectator> spectator = spectatorService.findById(id);
        if (spectator.isPresent()) {
            return new ResponseEntity<>(spectator.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to find spectator with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/token/{tokenId}")
    public ResponseEntity findByTokenId(@PathVariable("tokenId") String tokenId){
        Optional<Spectator> spectator = spectatorService.findByTokenId(tokenId);
        if (spectator.isPresent()) {
            return new ResponseEntity<>(spectator.get().getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to find spectator with token id " + tokenId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody Spectator spectator){
        Optional<Spectator> checkSpectator = spectatorService.findByTokenId(spectator.getTokenId());
        if(checkSpectator.isEmpty()){
            spectatorService.save(spectator);
            return new ResponseEntity<>(spectator, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Spectator allready exist " + spectator, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Spectator spectator){
        if (spectator != null){
            spectatorService.save(spectator);
            return new ResponseEntity(spectator, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Failed to create spectator " + spectator, HttpStatus.BAD_REQUEST);
        }
    }
}
