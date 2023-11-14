package com.example.hovedopgave_game_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    @JsonBackReference
    private Organizer organizer;

    @OneToMany(mappedBy = "competition")
    @JsonBackReference
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "competition")
    @JsonBackReference
    private List<AccessCode> accessCodes = new ArrayList<>();
}
