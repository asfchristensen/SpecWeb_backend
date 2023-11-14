package com.example.hovedopgave_game_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;

    // Quizzes can have one spectator/winner
    @ManyToOne
    @JoinColumn(name = "spectator_id")
    private Spectator spectator;
    // Quizzes can have one competition
    @ManyToOne
    @JoinColumn(name = "competition_id")
    @JsonBackReference
    private Competition competition;
    //Quizzes can have one state
    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonBackReference
    private State state;

    // One Quiz can have many answers
    @OneToMany(mappedBy = "quiz")
    @JsonBackReference
    private List<Answer> answers = new ArrayList<>();

}
