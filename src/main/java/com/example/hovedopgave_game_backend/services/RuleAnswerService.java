package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.RuleAnswer;
import com.example.hovedopgave_game_backend.repositories.RuleAnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleAnswerService implements IRuleAnswerService {

    @Autowired
    private RuleAnswerRepo ruleAnswerRepo;

    @Override
    public List<RuleAnswer> findAll() {
        return ruleAnswerRepo.findAll();
    }

    @Override
    public RuleAnswer save(RuleAnswer object) {
        return ruleAnswerRepo.save(object);
    }

    @Override
    public void delete(RuleAnswer object) {
        ruleAnswerRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ruleAnswerRepo.deleteById(aLong);
    }

    @Override
    public Optional<RuleAnswer> findById(Long aLong) {
        return ruleAnswerRepo.findById(aLong);
    }

    @Override
    public List<RuleAnswer> getAnswerByRuleId(long id) {
        return ruleAnswerRepo.getAnswerByRuleId(id);
    }
}
