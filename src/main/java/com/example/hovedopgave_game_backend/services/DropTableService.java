package com.example.hovedopgave_game_backend.services;

import com.example.hovedopgave_game_backend.models.Competition;
import com.example.hovedopgave_game_backend.models.Quiz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DropTableService {
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private QuizService quizService;

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(cron = "0 0 */3 * * *") // Execute every 3 hours
    @Transactional
    public void dropOldTables() {

        List<Competition> competitions = competitionService.findAll();
        List<Quiz> quizzes = quizService.findAll();
        LocalDate date = LocalDate.now();

        for (Competition competition:competitions) {
            System.out.println("Timestamp convert: " + Timestamp.parse(competition.getEndDate()));
            System.out.println("Todays Date: " + Timestamp.parse(competition.getEndDate()));

            //if (LocalDate.parse(competition.getEndDate()).dat < date)

        }


        // Drop tables older than 3 hours using JPQL
        String jpql = "DELETE FROM YourEntity e WHERE e.createdAt < :threshold";
        entityManager.createQuery(jpql)
                .setParameter("threshold", LocalDateTime.now().minusHours(3))
                .executeUpdate();
    }
    }
}
