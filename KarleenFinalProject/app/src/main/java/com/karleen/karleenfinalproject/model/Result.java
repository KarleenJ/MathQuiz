package com.karleen.karleenfinalproject.model;

import java.io.Serializable;

public class Result implements Serializable, Comparable {

    private int realAnswer, userAnswer;
    private String question, result;

    public Result(int realAnswer, int userAnswer, String question, String result) {
        this.realAnswer = realAnswer;
        this.userAnswer = userAnswer;
        this.question = question;
        this.result = result;
    }

    public int getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(int realAnswer) {
        this.realAnswer = realAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Q = " + question +
                " = " + realAnswer +
                " | you answered = " + userAnswer +
                " | " + result;
    }

    @Override
    public int compareTo(Object o) {
        return ((Result)o).getResult().compareTo(this.getResult());
    }
}
