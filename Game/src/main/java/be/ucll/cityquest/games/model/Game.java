package be.ucll.cityquest.games.model;

import be.ucll.cityquest.games.JpaConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String city;

    @Convert(converter = JpaConverter.ForCoordinates.class)
    private Coordinates coordinates;
    private String description;

    @Lob
    @Convert(converter = JpaConverter.ForQuestionList.class)
    private List<Question> questions = new ArrayList<>();

    public Game() {
        //why: Jackson deserialization
    }

    public Game(String name, String city, Coordinates coordinates, String description) {
        this.name = name;
        this.city = city;
        this.coordinates = coordinates;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public static class GameBuilder {

        private String name;
        private String city;
        private Coordinates coordinates;
        private String description;
        private List<Question> questions = new ArrayList<>();
        
        public static GameBuilder aGame() {
            return new GameBuilder();
        }

        public GameBuilder withName(String name) {
            this.name = name; return this;
        }

        public GameBuilder withCity(String city) {
            this.city = city; return this;
        }

        public GameBuilder withCoordinates(double lat, double lon) {
            return this.withCoordinates(new Coordinates(lat, lon));
        }

        public GameBuilder withCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates; return this;
        }

        public GameBuilder withDescription(String description) {
            this.description = description; return this;
        }

        public GameBuilder withQuestions(List<Question> questions) {
            this.questions = questions; return this;
        }

        public GameBuilder withQuestion(Question.QuestionBuilder questionBuilder) {
            return withQuestion(questionBuilder.build());
        }

        public GameBuilder withQuestion(Question question) {
            this.questions.add(question); return this;
        }

        public Game build() {
            Game game = new Game();
            game.name = name;
            game.city = city;
            game.coordinates = coordinates;
            game.description = description;
            game.questions = questions;
            return game;
        }
    } 
}
