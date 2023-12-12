package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.RuleAnswer;

import java.util.List;

public interface IRuleAnswerService extends CRUDService <RuleAnswer, Long>{
    List<RuleAnswer> getAnswerByRuleId(long id);
}
