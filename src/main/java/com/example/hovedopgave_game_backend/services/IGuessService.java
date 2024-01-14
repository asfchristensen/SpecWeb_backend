package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Guess;

import java.util.List;

public interface IGuessService extends CRUDService <Guess, Long>{
    List<Guess> findBySpectatorId(long specId);
}
