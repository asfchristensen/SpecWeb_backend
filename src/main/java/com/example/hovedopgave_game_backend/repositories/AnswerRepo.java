package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository <Answer, Long> {
    List<Answer> getAnswerByQuizId(long id);
}
