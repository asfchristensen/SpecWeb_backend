package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.*;
import com.example.hovedopgave_game_backend.repositories.CompetitionRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CompetitionService implements ICompetitionService {
    @Autowired
    private CompetitionRepo competitionRepo;

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private AnswerService answerService;

    @Override
    public List<Competition> findAll() {
        return competitionRepo.findAll();
    }
    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepo.findById(id);
    }

    @Override
    public void delete(Competition competition) {
        competitionRepo.delete(competition);
    }

    @Override
    public void deleteById(Long id) {
        competitionRepo.deleteById(id);
    }

    @Override
    public Competition save(Competition competition) {
        return competitionRepo.save(competition);
    }

    @Override
    public List<Competition> getAllByOrganizer_Id(long id) {
        return competitionRepo.getAllByOrganizer_Id(id);
    }

    //Service to create competition with some default quizzes
    public Competition createCompetitionWithDefaultQuizzes(Competition competition, Long organizerId, List<Quiz> quizzes){
        Optional<Organizer> organizer = organizerService.findById(organizerId);
        if (organizer.isPresent()) {
            competition.setOrganizer(organizer.get());
            //save the competition
            Competition savedCompetition = competitionRepo.save(competition);
            for (Quiz quiz : quizzes) {
                //save quiz to competition
                quiz.setCompetition(savedCompetition);
                quizService.save(quiz);
                for (Answer a:quiz.getAnswers()) {
                    //save answer to quiz
                    a.setQuiz(quiz);
                    answerService.save(a);
                }
            }
            return savedCompetition;
        } else {
            return null;
        }
    }
}
