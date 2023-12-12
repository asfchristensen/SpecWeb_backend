package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.services.RuleAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("spectator/rule-answers")
public class RuleAnswerController {
    @Autowired
    private RuleAnswerService rulesAnswersService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(rulesAnswersService.findAll());
    }

    @GetMapping("{quizId}")
    public ResponseEntity getRuleAnswersByRuleID(@PathVariable("quizId")long quizId){
        return ResponseEntity.ok(rulesAnswersService.getAnswerByRuleId(quizId));
    }
}
