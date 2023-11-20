package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/competition/{compId}")
    public ResponseEntity getByCompetitionID(@PathVariable("compId") long compId){
        return ResponseEntity.ok(quizService.findAllByCompetitionId(compId));
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Quiz quiz){
        System.out.println("quiz create: " + quiz.toString());
        if (quiz != null){
            quizService.save(quiz);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Quiz: ", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity updateQuiz(@RequestBody Quiz newQuiz){
        if(quizService.findById(newQuiz.getId()).isPresent()){
            Quiz oldQuiz = quizService.findById(newQuiz.getId()).get();
            oldQuiz.setQuestion(newQuiz.getQuestion());
            if(newQuiz.getSpectator() != null){
                oldQuiz.setSpectator(newQuiz.getSpectator());
            }
            if (newQuiz.getState() != null){
                oldQuiz.setState(newQuiz.getState());
            }
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
