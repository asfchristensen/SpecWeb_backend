package com.example.hovedopgave_game_backend.controllers;

import com.example.hovedopgave_game_backend.models.*;
import com.example.hovedopgave_game_backend.services.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
//@RequestMapping("competitions")
public class CompetitionController {
    private CompetitionService competitionService;
    private OrganizerService organizerService;
    private QuizService quizService;
    private AnswerService answerService;
    private StateService stateService;

    public CompetitionController(CompetitionService competitionService, OrganizerService organizerService, QuizService quizService, StateService stateService, AnswerService answerService) {
        this.competitionService = competitionService;
        this.organizerService = organizerService;
        this.quizService = quizService;
        this.stateService = stateService;
        this.answerService = answerService;
    }

    /*
    //Test controller - create competition with default quizzes
    @PostMapping("/organizer/create/competition/{organizerId}")
    public ResponseEntity createCompetition(@PathVariable long organizerId, @RequestBody Competition competition){

        Optional<State> state = stateService.findById(Long.parseLong("3"));
        Quiz quiz = new Quiz();
        quiz.setQuestion("t1");
        quiz.setState(state.get());

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
        quiz1.setState(state.get());

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



        Competition savedCompetition =  competitionService.createCompetitionWithDefaultQuizzes(competition, organizerId, quizzes);

            return new ResponseEntity<>(savedCompetition, HttpStatus.OK);
    }
*/

    @GetMapping("/organizer/competitions")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(this.competitionService.findAll());
    }

    @GetMapping("/spectator/competitions/{id}")
    public ResponseEntity getByID(@PathVariable("id") long id){
        Optional<Competition> competition = competitionService.findById(id);
        if (competition.isPresent()) {
            return new ResponseEntity<>(competition.get(), HttpStatus.OK);
        } else {
            System.out.println("No competition found with id: " + id);
            return new ResponseEntity<>("No Competition found", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/organizer/competitions/{tokenId}")
    public ResponseEntity getByTokenID(@PathVariable("tokenId") String tokenId){

        Optional<Organizer> organizer = organizerService.findByTokenId(tokenId);
        if (organizer.isPresent()) {
            long organizerId = organizer.get().getId();
            List<Competition> competitions = competitionService.getAllByOrganizer_Id(organizerId);
            return new ResponseEntity<>(competitions, HttpStatus.OK);
        } else {
            System.out.println("No Organizer found with token_id: " + tokenId);
            return new ResponseEntity<>("No Organizer found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/organizer/competitions")
    public ResponseEntity create(@RequestBody Competition competition){
        Map<String, String> message = new HashMap<>();
        if (competition != null){
            competitionService.save(competition);
            message.put("Message", "Competition is created " + competition );
        } else {
            message.put("Message", "Failed to create Competition: " + competition);
        }
        return ResponseEntity.ok(message);
    }


}
