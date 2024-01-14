package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.AccessCode;
import com.example.hovedopgave_game_backend.models.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCodeRepo extends JpaRepository <AccessCode, Long> {
    AccessCode getBySpectator(Spectator spectator);
}
