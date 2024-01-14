package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.RuleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleAnswerRepo extends JpaRepository<RuleAnswer, Long> {
    List<RuleAnswer> getAnswerByRuleId(long id);
}
