package be.ucll.cityquest.games.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private Coordinates coordinates;
    private String question;
    private List<String> answers;
    private int correctAnswer;
    private String extraInfo;

    private Question() {
        //why: Jackson deserialisation
    }

    public Question(Coordinates coordinates, String question, int correctAnswer, String extraInfo) {
        this.coordinates = coordinates;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.extraInfo = extraInfo;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public static class QuestionBuilder {

        private Coordinates coordinates;
        private String question;
        private List<String> answers = new ArrayList<>();
        private int correctAnswer;
        private String extraInfo;
        
        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withCoordinates(double lat, double lon) {
            return this.withCoordinates(new Coordinates(lat, lon));
        }

        public QuestionBuilder withCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates; return this;
        }

        public QuestionBuilder withQuestion(String question) {
            this.question = question; return this;
        }

        public QuestionBuilder withAnswers(List<String> answers) {
            this.answers = answers; return this;
        }

        public QuestionBuilder withAnswer(String answer) {
            this.answers.add(answer); return this;
        }

        public QuestionBuilder withCorrectAnswer(int correctAnswer) {
            this.correctAnswer = correctAnswer; return this;
        }

        public QuestionBuilder withExtraInformation(String extraInfo) {
            this.extraInfo = extraInfo; return this;
        }

        public Question build() {
            Question question = new Question();
            question.coordinates = this.coordinates;
            question.question = this.question;
            question.answers = this.answers;
            question.correctAnswer = this.correctAnswer;
            question.extraInfo = this.extraInfo;
            return question;
        }
    }
}
