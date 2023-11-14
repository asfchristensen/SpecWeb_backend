package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.services.QuizService;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("organizer")
public class QuizController {
    @Autowired
    private QuizService quizService;


    @GetMapping("/organizer")
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


    @POST
    @Path("/quizzes")
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseEntity create(Quiz quiz){
        System.out.println("quiz create: " + quiz);
        Map<String, String> message = new HashMap<>();
        if (quiz != null){
            quizService.save(quiz);
            message.put("Message", "Competition is quiz " + quiz );
        } else {
            message.put("Message", "Failed to create Quiz: " + quiz);
        }
        return ResponseEntity.ok(message);
    }
}
