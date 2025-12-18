package com.sample;

import java.util.Arrays;

public class Question {

    private String questionId;
    private String[] possibleAnswers;

    public Question() {
    }

    public Question(String questionId, String[] possibleAnswers) {
        this.questionId = questionId;
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(String[] possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", possibleAnswers=" + Arrays.toString(possibleAnswers) +
                '}';
    }
}
