package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Guess;
import com.example.hovedopgave_game_backend.repositories.GuessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuessService implements IGuessService {
    @Autowired
    private GuessRepo guessRepo;

    @Override
    public List<Guess> findAll() {
        return guessRepo.findAll();
    }

    @Override
    public Guess save(Guess object) {
        return guessRepo.save(object);
    }

    @Override
    public void delete(Guess object) {
        guessRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        guessRepo.deleteById(aLong);
    }

    @Override
    public Optional<Guess> findById(Long aLong) {
        return guessRepo.findById(aLong);
    }


    @Override
    public List<Guess> findBySpectatorId(long specId) {
        return guessRepo.findBySpectatorId(specId);
    }
}
