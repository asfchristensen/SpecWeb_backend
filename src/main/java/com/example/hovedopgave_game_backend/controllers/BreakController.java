package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Break;
import com.example.hovedopgave_game_backend.services.BreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("spectator/breaks")
public class BreakController {

    @Autowired
    private BreakService breakService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(breakService.findAll());
    }

    @GetMapping("/{spectatorId}")
    public ResponseEntity getBySpecID(@PathVariable("spectatorId") long spectatorId){
        List<Break> breaks = breakService.findBySpectatorId(spectatorId);
        return new ResponseEntity<>(breaks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBreak(@RequestBody Break aBreak){
        if (aBreak != null){
            breakService.save(aBreak);
            return new ResponseEntity<>(aBreak, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create break: ", HttpStatus.BAD_REQUEST);
        }
    }
}
