package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    private TextView finalScore;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        finalScore = findViewById(R.id.finalScoreView);
        exitButton = findViewById(R.id.exit_button);

        //Get extras from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            //Get score from previous activity (in extras), then set text of finalScore.
            String fscore = extras.getString("Score");
            finalScore.setText(fscore);
        }

        //On click listener for exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Exit the application
                Intent closeApp = new Intent(Intent.ACTION_MAIN);
                closeApp.addCategory(Intent.CATEGORY_HOME);
                startActivity(closeApp);
            }
        });


    }
}