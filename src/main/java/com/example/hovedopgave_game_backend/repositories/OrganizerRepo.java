package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Competition;
import com.example.hovedopgave_game_backend.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizerRepo extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findByTokenId(Long aLong);
}
