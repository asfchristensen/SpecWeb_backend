package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.services.QuizService;
import jakarta.ws.rs.*;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("organizer/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;


    @GetMapping()
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

    @PostMapping()
    public ResponseEntity create(@RequestBody Quiz quiz){
        System.out.println("quiz create: " + quiz.toString());
        Map<String, String> message = new HashMap<>();
        if (quiz != null){
            quizService.save(quiz);
            message.put("Message", "Competition is quiz " + quiz );
        } else {
            message.put("Message", "Failed to create Quiz: " + quiz);
        }
        return ResponseEntity.ok(message);
    }

    @PutMapping()
    public ResponseEntity updateQuiz(@RequestBody Quiz newQuiz){
        if(quizService.findById(newQuiz.getId()).isPresent()){
            System.out.println("1");
            Quiz oldQuiz = quizService.findById(newQuiz.getId()).get();
            oldQuiz.setQuestion(newQuiz.getQuestion());
            oldQuiz.setSpectator(newQuiz.getSpectator());
            oldQuiz.setState(newQuiz.getState());
            quizService.save(oldQuiz);
            return new ResponseEntity(oldQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity("not updated", HttpStatus.BAD_REQUEST);
        }
    }

    //quiz med tilhørende answers slettes
    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuiz(@PathVariable ("id") long id){
        Map<String, String> message = new HashMap<>();
        if(quizService.findById(id).isPresent()){
            quizService.deleteById(id);
            message.put("Message", "quiz deleted with id " + id );
        }else {
            message.put("Message", "no quiz with id " + id );
        }
        return ResponseEntity.ok(message);
    }
}
