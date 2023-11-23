package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.AccessCode;
import com.example.hovedopgave_game_backend.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessCodeRepo extends JpaRepository <AccessCode, Long> {
}
