package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button butStart;
    public static EditText name;
    static String uname;
    public static String usrname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butStart = (Button) findViewById(R.id.stbutton);
        //start button to start with thq quiz

        butStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                //start new Activity using intent
                startActivity(i);
            }
        });
    }

    public void initViews() {
        name = (EditText) findViewById(R.id.username);
    }

    public static String getName() {
        return name.getText().toString();
    }


}
