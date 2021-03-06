package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.lab2.controller.NextQuestion;
import com.example.lab2.controller.Score;
import com.example.lab2.model.AllQuestions;
import com.example.lab2.model.Question;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_INDEX = "GAME_MAIN_ACTIVITY";

    private TextView questionView;
    private TextView scoreView;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button doneButton;

    AllQuestions allQuestions = new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion();
    Score score = new Score();

    //Go to summary activity
    public void goToSum(){
        Intent summaryIntent = new Intent(MainActivity.this, SummaryActivity.class);
        //Pass final score to summary
        summaryIntent.putExtra("Score","Score: " + String.valueOf(score.getScore()));
        startActivity(summaryIntent);
    }

    //Code to generalize button behavior
    public void btnBehavior(int id){
        //Activities performed by all buttons
        int index = nextQuestion.getCurrentQuestion();
        Question question = null;
        try {
            question = allQuestions.getQuestion(index);
        } catch (Exception e) {
            Log.d(TAG_INDEX, "index out of bounds");
        }

        //Set score based on button clicked and if question is true
        //Activities performed by true button only
        if (question.isQuestionTrue() && id == 0) {
            score.correctAnswer();
        }
        //Activities performed by false button only
        else if (!question.isQuestionTrue() && id == 1) {
            score.correctAnswer();
        }
        //Activities performed by next button only
        else{
            score.skipAnswer();
        }

        //Set text for score view and go to next question. If next question is out of bounds, go to summary activity
        scoreView.setText("Score: " + String.valueOf(score.getScore()));
        index = nextQuestion.getNextQuestionIndex();
        if (index != -1){
            questionView.setText(allQuestions.getQuestion(index).getQuestionID());
        }
        else{
            goToSum();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up views and buttons based on content_main
        questionView = findViewById(R.id.questionView);
        scoreView = findViewById(R.id.scoreView);

        questionView.setText(R.string.q_start);
        scoreView.setText(R.string.initial_score);

        trueButton = findViewById(R.id.t_button);
        falseButton = findViewById(R.id.f_button);
        nextButton = findViewById(R.id.next_button);
        doneButton = findViewById(R.id.done_button);

        //On click listeners for each button, behavior defined in btnBehavior and goToSum
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBehavior(0);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBehavior(1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBehavior(2);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSum();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}