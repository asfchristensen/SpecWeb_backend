package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CompetitionRepo extends JpaRepository <Competition, Long>{
}
