package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository <Quiz, Long> {
}
