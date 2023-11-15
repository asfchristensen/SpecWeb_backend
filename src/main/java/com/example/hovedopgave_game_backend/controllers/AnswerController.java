package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Answer;
import com.example.hovedopgave_game_backend.repositories.AnswerRepo;
import com.example.hovedopgave_game_backend.repositories.StatesRepo;
import com.example.hovedopgave_game_backend.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(answerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Answer> answer = answerService.findById(id);
        if (answer.isPresent()) {
            return new ResponseEntity<>(answer.get(), HttpStatus.OK);
        } else {
            System.out.println("No Answer found with id: " + id);
            return new ResponseEntity<>("No Answer found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity getByQuizID(@PathVariable("quizId")long quizId){
        return ResponseEntity.ok(answerService.getAnswerByQuizId(quizId));
    }
}
