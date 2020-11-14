package com.example.it17068406;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;


public class JokeDashboard extends AppCompatActivity {
    RequestQueue queue;
    String url ="https://official-joke-api.appspot.com/random_joke";
    TextView txtjokes,txtid,txtType,txtSetup,txtpunch;
    ProgressBar progressBar;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_dashborad);
        queue= Volley.newRequestQueue(this);
        txtjokes=findViewById(R.id.textJokes);
        txtid=findViewById(R.id.txtID);
        txtType=findViewById(R.id.textType);
        txtSetup=findViewById(R.id.textSetup);
        txtpunch=findViewById(R.id.textPunch);

        progressBar=findViewById(R.id.progressBar);

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(JokeDashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getJokes(View view) {
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int ID=0;
                String type,setup,punch;
                try {
                    ID=response.getInt("id");
                    type=response.getString("type");
                    setup=response.getString("setup");
                    punch=response.getString("punchline");
                    Joke joke=new Joke(ID,type,setup,punch);
                    txtType.setText(joke.getType()+"");
                    txtType.setVisibility(View.VISIBLE);
                    txtSetup.setText(joke.getSetup()+"");
                    txtSetup.setVisibility(View.VISIBLE);
                    txtpunch.setText(joke.getPunchLine()+"");
                    txtpunch.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err=error.toString();
                txtjokes.setText("Cannot Get Data: " +error.toString());
            }
    });
    queue.add(jsonObjectRequest);


    }
}