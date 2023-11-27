package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Answer;
import com.example.hovedopgave_game_backend.models.Guesses;
import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.repositories.QuizRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {
    @Autowired
    QuizRepo quizRepo;
    private QuizService quizService;

    @BeforeEach
    public void setUp(){
        quizService = new QuizService(quizRepo);
    }

    @Test
    void testGetGuessMapForQuiz() {
        // Arrange

        //initialissere objecter
        Quiz quiz = new Quiz();

        Spectator spectator = new Spectator();

        Guesses guesses1 = new Guesses();
        Guesses guesses2 = new Guesses();
        Guesses guesses3 = new Guesses();

        Answer correctAnswer1 = new Answer();
        Answer correctAnswer2 = new Answer();
        Answer incorrectAnswer = new Answer();

        //setter answers værdier
        correctAnswer1.setCorrect(true);
        correctAnswer1.setGuesses(List.of(guesses1));
        correctAnswer2.setCorrect(true);
        correctAnswer2.setGuesses(List.of(guesses2));
        incorrectAnswer.setCorrect(false);
        incorrectAnswer.setGuesses(List.of(guesses3));

        //setter guesses værdier
        guesses1.setSpectator(spectator);
        guesses1.setAnswer(correctAnswer1);
        guesses3.setSpectator(spectator);
        guesses3.setAnswer(correctAnswer2);
        guesses2.setSpectator(spectator);
        guesses2.setAnswer(incorrectAnswer);

        //setter quiz værdier
        List<Answer> answers = List.of(correctAnswer1, incorrectAnswer, correctAnswer2);
        quiz.setAnswers(answers);

        Optional<Quiz> optionalQuiz = Optional.of(quiz);

        // Act
        Map<String, Integer> result = this.quizService.getGuessMapForQuiz(optionalQuiz);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.get("Guesses"));
        assertEquals(2, result.get("CorrectGuesses"));
    }
}
