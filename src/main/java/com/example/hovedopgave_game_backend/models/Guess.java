package com.example.hovedopgave_game_backend.models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "guesses")
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "spectator_id")
    private Spectator spectator;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
