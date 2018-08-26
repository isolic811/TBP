package com.example.kvizolina.kvizolina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.model.Player;
import com.example.model.Question;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.Vector;

public class RangListActivity extends AppCompatActivity {

    Vector<Player> players=new Vector<Player>();
    TextView tvRepoList;  // This will reference our repo list text box.
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rang_list);

        this.tvRepoList = (TextView) findViewById(R.id.tv_repo_list);  // Link our repository list text output box.
        tvRepoList.setText("Username                        Points");
        this.ll = (LinearLayout) findViewById(R.id.LinearLayout01);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="192.168.1.3:8000/api/topTen";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, (JSONArray) null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int n = 0; n < response.length(); n++)
                            {

                                JSONObject object = response.getJSONObject(n);
                                Player player=new Player(object.getInt("id"),object.getString("username"),object.getDouble("points"));
                                players.add(player);


                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fillRangList();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fillRangList();
                    }
                });



        queue.add(jsonArrayRequest);
    }

    private void fillRangList(){
        if (players.isEmpty()){
            TextView user = new TextView(this);
            user.setText("Ranglist can't be loaded right now! :(");
            user.setId(1);
            ll.addView(user);
        }
        else{
            for (Player player:players){
                TextView user = new TextView(this);
                user.setText(player.getUsername()+"                         "+String.valueOf(player.getPoints()));
                ll.addView(user);
            }
        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(RangListActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
