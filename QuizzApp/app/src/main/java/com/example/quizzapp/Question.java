package com.example.quizzapp;

import java.util.List;

// Question.java
public class Question {
    private String questionText;
    private List<String> answers;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getters
    public String getQuestionText() { return questionText; }
    public List<String> getAnswers() { return answers; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
}