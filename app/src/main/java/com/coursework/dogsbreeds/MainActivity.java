package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView difficulty;

    private static String difficulty_level = "You have chosen Beginner Level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        get difficulty text view
        difficulty = (TextView)findViewById(R.id.difficulty_description);
    }

    //change difficulty level
    public void changeDifficulty(View view) {
//        adding a log message
        Log.d(LOG_TAG, "Button Clicked (Changed Difficulty)");

        if (difficulty_level.equals("You have chosen Beginner Level")){
            difficulty_level = "You have chosen Intermediate Level";
        }else {
            difficulty_level = "You have chosen Beginner Level";
        }
//        set message
        difficulty.setText(difficulty_level);
    }
}
