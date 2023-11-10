package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepo extends JpaRepository<Organizer, Long> {
}
