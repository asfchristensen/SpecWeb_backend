package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Guess;
import com.example.hovedopgave_game_backend.services.GuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("spectator/guesses")
public class GuessController {
    @Autowired
    private GuessService guessService;

    @GetMapping
    public ResponseEntity getAllGuesses(){
        return new ResponseEntity(guessService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{spectatorId}")
    public ResponseEntity getByID(@PathVariable("spectatorId") long spectatorId){
        List<Guess> guesses = guessService.findBySpectatorId(spectatorId);
        return new ResponseEntity<>(guesses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createGuess(@RequestBody Guess guess){
        if (guess != null){
            guessService.save(guess);
            return new ResponseEntity<>(guess, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create guess " + guess, HttpStatus.BAD_REQUEST);
        }
    }
}
