package com.example.hovedopgave_game_backend.services;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T, ID> {
    List<T> findAll();

    T save (T object);

    void delete (T object);

    void deleteById (ID id);

    Optional<T> findById (ID id);
}
