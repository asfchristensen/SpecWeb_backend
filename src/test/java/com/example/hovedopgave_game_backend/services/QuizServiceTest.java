package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Answer;
import com.example.hovedopgave_game_backend.models.Guess;
import com.example.hovedopgave_game_backend.models.Quiz;
import com.example.hovedopgave_game_backend.models.Spectator;
import com.example.hovedopgave_game_backend.repositories.QuizRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

        //initializing objects
        Quiz quiz = new Quiz();

        Spectator spectator = new Spectator();

        Guess guess1 = new Guess();
        Guess guess2 = new Guess();
        Guess guess3 = new Guess();

        Answer correctAnswer1 = new Answer();
        Answer correctAnswer2 = new Answer();
        Answer incorrectAnswer = new Answer();

        //sets answers values
        correctAnswer1.setCorrect(true);
        correctAnswer1.setGuesses(List.of(guess1));
        correctAnswer2.setCorrect(true);
        correctAnswer2.setGuesses(List.of(guess2));
        incorrectAnswer.setCorrect(false);
        incorrectAnswer.setGuesses(List.of(guess3));

        //sets guess values
        guess1.setSpectator(spectator);
        guess1.setAnswer(correctAnswer1);
        guess3.setSpectator(spectator);
        guess3.setAnswer(correctAnswer2);
        guess2.setSpectator(spectator);
        guess2.setAnswer(incorrectAnswer);

        //sets quiz values
        List<Answer> answers = List.of(correctAnswer1, incorrectAnswer, correctAnswer2);
        quiz.setAnswers(answers);

        Optional<Quiz> optionalQuiz = Optional.of(quiz);

        // Act
        Map<String, Integer> result = this.quizService.getGuessMapForQuiz(optionalQuiz);

        // Assert
        //checks not null
        assertNotNull(result);
        //checks that the key from Guesses the correct answer = 3
        assertEquals(3, result.get("Guesses"));
        //checks that the key CorrectGuesses has the correct answer = 2
        assertEquals(2, result.get("CorrectGuesses"));
    }

    @Test
    void getSpectatorsWithCorrectGuess() {
        // Arrange

        //initilize objects
        Spectator spectator = new Spectator();
        Spectator spectator1 = new Spectator();
        Spectator spectator2 = new Spectator();

        Guess guess1 = new Guess();
        Guess guess2 = new Guess();
        Guess guess3 = new Guess();
        Guess guess4 = new Guess();

        Answer correctAnswer1 = new Answer();
        Answer correctAnswer2 = new Answer();
        Answer correctAnswer3 = new Answer();
        Answer incorrectAnswer = new Answer();

        //sets answers values
        correctAnswer1.setCorrect(true);
        correctAnswer1.setGuesses(List.of(guess1));

        correctAnswer2.setCorrect(true);
        correctAnswer2.setGuesses(List.of(guess2));

        incorrectAnswer.setCorrect(false);
        incorrectAnswer.setGuesses(List.of(guess3));

        correctAnswer3.setCorrect(true);
        correctAnswer3.setGuesses(List.of(guess4));

        //sets guesses values
        guess1.setSpectator(spectator);
        guess1.setAnswer(correctAnswer1);
        guess3.setSpectator(spectator);
        guess3.setAnswer(correctAnswer2);
        guess2.setSpectator(spectator2);
        guess2.setAnswer(incorrectAnswer);
        guess4.setAnswer(correctAnswer3);
        guess4.setSpectator(spectator1);

        Quiz quiz = new Quiz();
        //sets quiz value
        List<Answer> answers = List.of(correctAnswer1, incorrectAnswer, correctAnswer2);
        quiz.setAnswers(answers);

        Optional<Quiz> optionalQuiz = Optional.of(quiz);

        // Act
        List<Spectator> spectators = this.quizService.getSpectatorsWithCorrectGuess(optionalQuiz);
        List<Spectator> spectatorsWhoAnsweredCorrect = new ArrayList<>();
        spectatorsWhoAnsweredCorrect.add(spectator);
        spectatorsWhoAnsweredCorrect.add(spectator2);


        // Assert

        //checks not null
        assertNotNull(spectators);
        //checks if the list has the correct size
        assertEquals(2, spectators.size());
        //checks if it is the correct spectators added to list
        assertIterableEquals(spectatorsWhoAnsweredCorrect, spectators);
    }
}
