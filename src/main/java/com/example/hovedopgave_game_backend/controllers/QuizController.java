package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Competition;
import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.repositories.QuizRepo;
import com.example.hovedopgave_game_backend.repositories.SpectatorRepo;
import com.example.hovedopgave_game_backend.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;


    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        } else {
            System.out.println("No Quiz found with id: " + id);
            return new ResponseEntity<>("No Quiz found", HttpStatus.BAD_REQUEST);
        }
    }
}
