package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Guesses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuessesRepo extends JpaRepository<Guesses, Long> {
}
