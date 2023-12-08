package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.*;
import com.example.hovedopgave_game_backend.repositories.AnswerRepo;
import com.example.hovedopgave_game_backend.repositories.CompetitionRepo;
import com.example.hovedopgave_game_backend.repositories.OrganizerRepo;
import com.example.hovedopgave_game_backend.repositories.QuizRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//DataJpaTest gør at vi kan bruge vores jpa database
@DataJpaTest
//Laver en database in memory med de configurationer vi også bruger i produktion - vi sætter ind fra changelogs og mockdata
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CompetitionServiceTest {
    //repos
    @Autowired
    private CompetitionRepo competitionRepo;

    @Autowired
    private OrganizerRepo organizerRepo;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private AnswerRepo answerRepo;

    //services
    private CompetitionService competitionService;
    private OrganizerService organizerService;
    private QuizService quizService;
    private AnswerService answerService;

    @BeforeEach
    public void setUp(){
        organizerService = new OrganizerService(this.organizerRepo);
        quizService = new QuizService(this.quizRepo);
        answerService = new AnswerService(this.answerRepo);
        competitionService = new CompetitionService(competitionRepo, organizerService, quizService, answerService);
    }

    @Test
    void createCompetitionWithDefaultQuizzes() {
        // Arrange
        Competition competition = new Competition();
        competition.setName("test");
        competition.setStartDate("2023-12-02 00:00:00+01");
        competition.setEndDate("2023-12-05 00:00:00+01");

        State state = new State();
        state.setId(3);

        Quiz quiz = new Quiz();
        quiz.setQuestion("t1");
        quiz.setState(state);

        List<Answer> answers1 = new ArrayList<>();
        Answer answer11 = new Answer();
        answer11.setAnswer("ja");
        answer11.setCorrect(true);
        answer11.setQuiz(quiz);
        answers1.add(answer11);

        Answer answer12 = new Answer();
        answer12.setAnswer("nej");
        answer12.setCorrect(false);
        answer12.setQuiz(quiz);
        answers1.add(answer12);

        Answer answer13 = new Answer();
        answer13.setAnswer("måske");
        answer13.setCorrect(false);
        answer13.setQuiz(quiz);
        answers1.add(answer13);

        Answer answer14 = new Answer();
        answer14.setAnswer("næhhhh");
        answer14.setCorrect(false);
        answer14.setQuiz(quiz);
        answers1.add(answer14);

        Quiz quiz1 = new Quiz();
        quiz1.setQuestion("t2");
        quiz1.setState(state);

        List<Answer> answers2 = new ArrayList<>();
        Answer answer21 = new Answer();
        answer21.setAnswer("ja1");
        answer21.setCorrect(true);
        answer21.setQuiz(quiz1);
        answers2.add(answer21);

        Answer answer22 = new Answer();
        answer22.setAnswer("nej1");
        answer22.setCorrect(false);
        answer22.setQuiz(quiz1);
        answers2.add(answer22);

        Answer answer23 = new Answer();
        answer23.setAnswer("måske1");
        answer23.setCorrect(false);
        answer23.setQuiz(quiz1);
        answers2.add(answer23);

        Answer answer24 = new Answer();
        answer24.setAnswer("næhhhh1");
        answer24.setCorrect(false);
        answer24.setQuiz(quiz1);
        answers2.add(answer24);

        quiz.setAnswers(answers1);
        quiz1.setAnswers(answers2);

        List<Quiz> quizzes = new ArrayList<>();

        quizzes.add(quiz);
        quizzes.add(quiz1);

        // Act
        Competition savedCompetition = competitionService.createCompetitionWithDefaultQuizzes(competition,Long.parseLong("1"),quizzes);

        List<Quiz> quizList = quizService.findAllByCompetitionId(savedCompetition.getId());

        List<Answer> answers = new ArrayList<>();
        for (Quiz q : quizList) {
            for (Answer a : q.getAnswers()) {
                answers.add(a);
            }
        }

        Competition badRequestCompetition = competitionService.createCompetitionWithDefaultQuizzes(competition,Long.parseLong("10"),quizzes);

        //Assert

        //tjekker at den competition der kommer tilbage ikke er null
        assertNotNull(savedCompetition);

        //tjekker der er added 2 quizzes til den nye competition
        assertEquals(2,quizList.size());

        //tjekker at alle answers er added til en quiz
        assertEquals(8, answers.size());

        //tjekker at metoden returnere null, hvis den får en organizer id der ikke findes
        assertNull(badRequestCompetition);
    }
}
