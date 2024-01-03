package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepo extends JpaRepository<Guess, Long> {
    List<Guess> findBySpectatorId(long specId);
}
