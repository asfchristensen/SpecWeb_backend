package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Break;

import java.util.List;

public interface IBreakService extends CRUDService <Break, Long>{
    List<Break> findBySpectatorId(long specId);
}
