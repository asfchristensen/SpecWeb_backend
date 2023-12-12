package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.Answer;
import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.repositories.AnswerRepo;
import com.example.hovedopgave_game_backend.repositories.StatesRepo;
import com.example.hovedopgave_game_backend.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("organizer/answers")
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
            return new ResponseEntity<>("No Answer found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity getByQuizID(@PathVariable("quizId")long quizId){
        return ResponseEntity.ok(answerService.getAnswerByQuizId(quizId));
    }

    @PostMapping()
    public ResponseEntity createAnswer(@RequestBody Answer answer){
        if (answer != null){
            answerService.save(answer);
            return new ResponseEntity<>(answer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create answer: ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/answers")
    public ResponseEntity createAnswers(@RequestBody List<Answer> answers){
        if (answers.size() > 0){
            for (Answer answer:answers) {
                answerService.save(answer);
            }
            return new ResponseEntity(answers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Failed to create answers", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity updateAnswersForAQuiz(@RequestBody List<Answer> answers){
        List<Answer> newAnswers = new ArrayList<>();

        if (answers.size() > 0){
            for (Answer answer:answers) {
                Answer newAnswer = answer;
                if (answerService.findById(newAnswer.getId()).isPresent()) {
                    Answer oldAnswer = answerService.findById(newAnswer.getId()).get();
                    oldAnswer.setAnswer(newAnswer.getAnswer());
                    oldAnswer.setCorrect(newAnswer.isCorrect());
                    answerService.save(oldAnswer);
                    newAnswers.add(oldAnswer);
                }
            } return new ResponseEntity(newAnswers, HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Failed to update answers", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAsnwer(@PathVariable ("id") long id){
        if(answerService.findById(id).isPresent()){
            answerService.deleteById(id);
            return new ResponseEntity("Answer deleted with id" + id, HttpStatus.OK);
        }else {
            return new ResponseEntity("Failed to find answer with id " + id, HttpStatus.NOT_FOUND);
        }
    }
}
