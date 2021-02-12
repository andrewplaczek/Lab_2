package com.example.lab2.model;
import com.example.lab2.model.Question;
import com.example.lab2.R;

public class AllQuestions {
    //Store the index of the current question (Not sure if needed)
    private int currentQ = 0;

    public Question[] allQuestions = {
            new Question(R.string.q_start,true),
            new Question(R.string.q_continent,true),
            new Question(R.string.q_seas,false),
            new Question(R.string.q_add3_4,true),
            new Question(R.string.q_add5_4,false),
            new Question(R.string.q_meteorology,true),
            new Question(R.string.q_pearls,false),
            new Question(R.string.q_jupiter,false),
            new Question(R.string.q_taste,true),
            new Question(R.string.q_vc,true)
    };

    public Question getQuestion(int i){
        return allQuestions[i];
    }
}
