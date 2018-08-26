package com.example.kvizolina.kvizolina;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.model.Question;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class QuizActivity extends AppCompatActivity {

    Vector<Question> listOfQuestions=new Vector<Question>();

    double points, elapsedSeconds;
    int correctAnswered;
    long startTime,endTime;
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
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="192.168.1.3:8000/api/questions";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, (JSONArray) null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int n = 0; n < response.length(); n++)
                            {
                                JSONObject object = response.getJSONObject(n);
                                JSONObject njo=object.getJSONObject("level");
                                String strQuestion=object.getString("question_text");
                                String lvl=object.getString(njo.getString("name"));
                                String correct_answer=object.getString("correct_answer");
                                String a1=object.getString("answer1");
                                String a2=object.getString("answer1");
                                String a3=object.getString("answer1");

                                Question question=new Question(strQuestion,lvl,correct_answer,a1,a2,a3);
                                listOfQuestions.add(question);
                            }
                        }
                         catch (JSONException e) {
                            e.printStackTrace();
                        }

                        localOrApi();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        localOrApi();
                    }
                });



        queue.add(jsonArrayRequest);

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
        startTime=System.currentTimeMillis();
        mixList.clear();
    }

    public void answered(View view) {
        endTime=System.currentTimeMillis();
        elapsedSeconds=(endTime-startTime)/1000.0;
        Button b = (Button)view;
        String answer = b.getText().toString();
        if (answer.equals(listOfQuestions.firstElement().getCorrectAnswer())){
            if(listOfQuestions.firstElement().getLevel().equals("easy")){
                points+=10/elapsedSeconds;
            }
            else if (listOfQuestions.firstElement().getLevel().equals("medium")){
                points+=20/elapsedSeconds;
            }
            else{
                points+=30/elapsedSeconds;
            }
            correctAnswered+=1;
        }
        listOfQuestions.remove(0);
        if (!listOfQuestions.isEmpty()){
            scoreTextView.setText(String.valueOf(new BigDecimal(points).setScale(2, RoundingMode.HALF_UP).doubleValue()));


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
