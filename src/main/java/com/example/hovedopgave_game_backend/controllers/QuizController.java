package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.services.DropTableService;
import com.example.hovedopgave_game_backend.services.QuizService;
import com.example.hovedopgave_game_backend.services.SpectatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private SpectatorService spectatorService;

    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/organizer/quizzes/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Quiz found with id " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/spectator/quizzes/competition/{compId}")
    public ResponseEntity getByCompetitionID(@PathVariable("compId") long compId){
        return ResponseEntity.ok(quizService.findAllByCompetitionId(compId));
    }

    @GetMapping("/organizer/quizzes/guesses/{id}")
    public ResponseEntity getGuessesByQuizId(@PathVariable ("id") long id){
        Optional<Quiz> quiz = quizService.findById(id);
        if (quiz.isPresent()){
            Map<String, Integer> map = quizService.getGuessMapForQuiz(quiz);
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            return new ResponseEntity("No quiz with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organizer/quizzes/winner/{id}")
    public ResponseEntity getWinner(@PathVariable ("id") long id){
        Optional<Quiz> quiz = quizService.findById(id);

        if(quiz.isPresent()){
            List<Spectator> spectators = quizService.getSpectatorsWithCorrectGuess(quiz);
            Spectator winner = spectatorService.getRandomSpecator(spectators);
            quiz.get().setSpectator(winner);
            quizService.save(quiz.get());
            return new ResponseEntity(winner, HttpStatus.OK);
        } else {
            return new ResponseEntity("Failed to find quiz with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/organizer/quizzes")
    public ResponseEntity create(@RequestBody Quiz quiz){
        if (quiz != null){
            quizService.save(quiz);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Quiz: " + quiz, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/organizer/quizzes")
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
            if (newQuiz.getTime() != null){
                oldQuiz.setTime(newQuiz.getTime());
            }
            quizService.save(oldQuiz);
            return new ResponseEntity(oldQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity("Failed to update quiz", HttpStatus.BAD_REQUEST);
        }
    }

    //quiz and its answers gets deleted
    @DeleteMapping("/organizer/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable ("id") long id){
        if(quizService.findById(id).isPresent()){
            quizService.deleteById(id);
            return new ResponseEntity("Quiz deleted with id " + id, HttpStatus.OK);
        }else {
            return new ResponseEntity("Failed to delete quiz with id " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
