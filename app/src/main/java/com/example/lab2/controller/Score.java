package com.example.lab2.controller;

public class Score {
    private int score;

    public void correctAnswer(){
        score += 10;
    }

    public void skipAnswer(){
        score -= 5;
    }

    public int getScore(){
        return score;
    }
}
