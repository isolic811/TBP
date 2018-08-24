package com.example.kvizolina.kvizolina;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


public class QuizActivity extends AppCompatActivity {

    Vector<Question> listOfQuestions=new Vector<Question>();
    double points;
    int correctAnswered;

    TextView questionTextView;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button answer4Button;
    TextView scoreTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.questionTextView=(TextView) this.findViewById(R.id.questionText);
        this.scoreTextView=(TextView) this.findViewById(R.id.score);
        this.answer1Button= (Button) this.findViewById(R.id.answer1);
        this.answer2Button=(Button) this.findViewById(R.id.answer2);
        this.answer3Button= (Button) this.findViewById(R.id.answer3);
        this.answer4Button= (Button) this.findViewById(R.id.answer4);

        localOrApi();
    }
    private void localOrApi(){
        if (listOfQuestions.isEmpty()){
            listOfQuestions=Question.localQuestions();
        }
        points=0.0;
        correctAnswered=0;
        showQuestion();
    }
    private void showQuestion(){
        ArrayList<String> mixList=new ArrayList<String>();
        mixList.add(listOfQuestions.firstElement().getCorrectAnswer());
        mixList.add(listOfQuestions.firstElement().getAns1());
        mixList.add(listOfQuestions.firstElement().getAns2());
        mixList.add(listOfQuestions.firstElement().getAns3());
        Collections.shuffle(mixList);
        questionTextView.setText(listOfQuestions.firstElement().getQuestionText());
        answer1Button.setText(mixList.get(0));
        answer2Button.setText(mixList.get(1));
        answer3Button.setText(mixList.get(2));
        answer4Button.setText(mixList.get(3));
        mixList.clear();
    }

    public void answered(View view) {
        Button b = (Button)view;
        String answer = b.getText().toString();
        if (answer.equals(listOfQuestions.firstElement().getCorrectAnswer())){
            points+=10;
            correctAnswered+=1;
        }
        listOfQuestions.remove(0);
        if (!listOfQuestions.isEmpty()){
            scoreTextView.setText(String.valueOf(points));
            showQuestion();
        }
        else{
            Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
            intent.putExtra("score", points);
            intent.putExtra("correctAnswers",correctAnswered);
            startActivity(intent);
        }
    }
}
