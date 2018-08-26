package com.example.kvizolina.kvizolina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        finalScore.setText(String.valueOf(new BigDecimal(points).setScale(2, RoundingMode.HALF_UP).doubleValue()));
        correctAns.setText(String.valueOf(correctAnswered)+"/10 correct answered");

    }

    public void backToMenu(View view) {
        Intent intent = new Intent(QuizEndActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void submitResult(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://127.0.0.1:8000/api/rangList/?username="+username.getText()+"&points="+finalScore;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(QuizEndActivity.this, RangListActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(QuizEndActivity.this, RangListActivity.class);
                startActivity(intent);
            }
        });

        queue.add(stringRequest);


    }
}
