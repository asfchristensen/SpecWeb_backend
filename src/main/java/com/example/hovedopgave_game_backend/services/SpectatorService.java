package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.repositories.SpectatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpectatorService implements ISpectatorService {
    @Autowired
    private SpectatorRepo spectatorRepo;

    @Override
    public List<Spectator> findAll() {
        return spectatorRepo.findAll();
    }

    @Override
    public Spectator save(Spectator spectator) {
        return spectatorRepo.save(spectator);
    }

    @Override
    public void delete(Spectator spectator) {
        spectatorRepo.delete(spectator);
    }

    @Override
    public void deleteById(Long id) {
        spectatorRepo.deleteById(id);
    }

    @Override
    public Optional<Spectator> findById(Long id) {
        return spectatorRepo.findById(id);
    }

    @Override
    public Optional<Spectator> findByTokenId(String tokenId) {
        return spectatorRepo.findByTokenId(tokenId);
    }
}
