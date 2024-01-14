package com.example.hovedopgave_game_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "spectators")
public class Spectator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String mail;
    @Column(name = "token_id")
    private String tokenId;

    @OneToMany(mappedBy = "spectator")
    @JsonBackReference
    private List<Quiz> quizzes = new ArrayList<>();

}
