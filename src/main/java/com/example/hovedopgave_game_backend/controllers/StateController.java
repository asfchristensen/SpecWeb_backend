package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.State;
import com.example.hovedopgave_game_backend.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("spectator/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(stateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<State> state = stateService.findById(id);
        if (state.isPresent()) {
            return new ResponseEntity<>(state.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to find state with id " + id, HttpStatus.NOT_FOUND);
        }
    }
}
