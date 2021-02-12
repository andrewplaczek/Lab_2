package com.example.lab2.controller;
import com.example.lab2.model.AllQuestions;

import android.util.Log;

import com.example.lab2.model.Question;

public class NextQuestion {
    private int index = 0;
    AllQuestions allQuestions = new AllQuestions();

    public int getCurrentQuestion(){
        return index;
    }

    //Sets next question index and returns if it is in bounds
    public int getNextQuestionIndex(){
        index++;

        Question question = null;
        try {
            question = allQuestions.getQuestion(index);
        } catch (Exception e) {
            index = -1;
        }

        return index;
    }
}
