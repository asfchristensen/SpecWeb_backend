package com.example.hovedopgave_game_backend.repositories;

import com.example.hovedopgave_game_backend.models.Break;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreakRepo extends JpaRepository<Break, Long> {
    List<Break> findBySpectatorId(long specId);
}
