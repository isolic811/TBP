package com.example.kvizolina.kvizolina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuizEndActivity extends AppCompatActivity {


    TextView finalScore;
    TextView correctAns;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        Intent intent = getIntent();
        double points = intent.getExtras().getDouble("score");
        int correctAnswered=intent.getExtras().getInt("correctAnswers");
        this.finalScore=(TextView) this.findViewById(R.id.score);
        this.correctAns=(TextView) this.findViewById(R.id.correctAnswers);
        this.username= (EditText) this.findViewById(R.id.usernameInput);
        finalScore.setText(String.valueOf(points));
        correctAns.setText(String.valueOf(correctAnswered)+"/10 correct answered");

    }

    public void backToMenu(View view) {
        Intent intent = new Intent(QuizEndActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void submitResult(View view) {
        Intent intent = new Intent(QuizEndActivity.this, RangListActivity.class);
        startActivity(intent);
    }
}
