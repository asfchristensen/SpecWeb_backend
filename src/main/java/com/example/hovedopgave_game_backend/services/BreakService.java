package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Break;
import com.example.hovedopgave_game_backend.repositories.BreakRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreakService implements IBreakService {

    @Autowired
    private BreakRepo breakRepo;

    @Override
    public List<Break> findAll() {
        return breakRepo.findAll();
    }

    @Override
    public Break save(Break object) {
        return breakRepo.save(object);
    }

    @Override
    public void delete(Break object) {
        breakRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        breakRepo.deleteById(aLong);
    }

    @Override
    public Optional<Break> findById(Long aLong) {
        return breakRepo.findById(aLong);
    }

    @Override
    public List<Break> findBySpectatorId(long specId) {
        return breakRepo.findBySpectatorId(specId);
    }
}
