package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Guesses;
import com.example.hovedopgave_game_backend.services.GuessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("spectator/guesses")
public class GuessesController {
    @Autowired
    private GuessesService guessesService;

    @GetMapping
    public ResponseEntity getAllGuesses(){
        return new ResponseEntity(guessesService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createGuess(@RequestBody Guesses guesses){
        System.out.println("guess = " + guesses);
        if (guesses != null){
            guessesService.save(guesses);
            return new ResponseEntity<>(guesses, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Quiz: ", HttpStatus.BAD_REQUEST);
        }
    }


}
