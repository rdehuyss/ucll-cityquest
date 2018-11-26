package be.ucll.cityquest;

import be.ucll.cityquest.games.GameRepository;
import be.ucll.cityquest.games.model.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static be.ucll.cityquest.games.model.Game.GameBuilder.aGame;
import static be.ucll.cityquest.games.model.Question.QuestionBuilder.aQuestion;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(GameRepository repository) {
        return (args) -> {
            Game game = aGame()
                    .withName("Leuven Treasure Hunt")
                    .withCity("Leuven")
                    .withCoordinates(50.8798, 4.7005)
                    .withDescription("Follow the map of questions and find the treasure!")
                    .withQuestion(aQuestion()
                            .withCoordinates(50.879154, 4.704496)
                            .withQuestion("What is the main topic in the M-Museum")
                            .withAnswer("National History")
                            .withAnswer("Fashion")
                            .withAnswer("Modern art")
                            .withCorrectAnswer(3)
                            .withExtraInformation("Art museum opened in 2009, architect-designed to unite old buildings with contemporary architecture."))
                    .withQuestion(aQuestion()
                            .withCoordinates(50.879127, 4.701235)
                            .withQuestion("How many statues are on the walls?")
                            .withAnswer("196")
                            .withAnswer("236")
                            .withAnswer("266")
                            .withCorrectAnswer(2)
                            .withExtraInformation("15th-century, Gothic-style, former municipal headquarters with spires & 236 sculptures on the walls."))
                    .build();

            repository.save(game);
        };
    }
}
