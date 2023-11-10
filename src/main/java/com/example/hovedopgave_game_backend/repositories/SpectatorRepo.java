package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpectatorRepo extends JpaRepository <Spectator, Long> {
}
