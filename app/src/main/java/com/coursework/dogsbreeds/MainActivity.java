package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView difficulty;

    private static String difficulty_level = "You have chosen Beginner Level";

    private static HashMap<String, String[]> imagesMap = new HashMap<>();

    private static boolean gameDifficulty = false;

    public void addImages() {
        String[] bullMastiff = new String[]{"bull1", "bull2", "bull3", "bull4", "bull5"};
        String[] doberman = new String[]{"doberman1", "doberman2", "doberman3", "doberman4", "doberman5"};
        String[] eskimo = new String[]{"eskimo1", "eskimo2", "eskimo3", "eskimo4", "eskimo5"};
        String[] germanShepard = new String[]{"german1", "german2", "german3", "german4", "german5"};
        String[] goldenRetriever = new String[]{"golden1", "golden2", "golden3", "golden4", "golden5"};
        String[] siberianHusky = new String[]{"husky1", "husky2", "husky3", "husky4", "husky5"};
        String[] komondor = new String[]{"komondor1", "komondor2", "komondor3", "komondor4", "komondor5"};
        String[] labradorRetriever = new String[]{"labrador1", "labrador2", "labrador3", "labrador4", "labrador5"};
        String[] rottweiler = new String[]{"rotweiller1", "rotweiller2", "rotweiller3", "rotweiller4", "rotweiller5"};
        String[] saint = new String[]{"saint1", "saint2", "saint3", "saint4", "saint5"};

        imagesMap.put("Bull Mastiff", bullMastiff);
        imagesMap.put("Doberman", doberman);
        imagesMap.put("Eskimo", eskimo);
        imagesMap.put("German Shepard", germanShepard);
        imagesMap.put("Golden Retriever", goldenRetriever);
        imagesMap.put("Siberian Husky", siberianHusky);
        imagesMap.put("Komondor", komondor);
        imagesMap.put("Labrador Retriever", labradorRetriever);
        imagesMap.put("Bull Rottweiler", rottweiler);
        imagesMap.put("Saint", saint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        get difficulty text view
        difficulty = (TextView) findViewById(R.id.difficulty_description);

        addImages();
    }

    //change difficulty level
    public void changeDifficulty(View view) {
//        adding a log message
        Log.d(LOG_TAG, "Button Clicked (Changed Difficulty)");


//        adding an if else to change the difficulty value
        if (difficulty_level.equals("You have chosen Beginner Level")) {
            difficulty_level = "You have chosen Intermediate Level";
//        setting boolean value true if the difficulty is intermediate
            gameDifficulty = true;
        } else {
            difficulty_level = "You have chosen Beginner Level";
            gameDifficulty = false;
        }
//        set message
        difficulty.setText(difficulty_level);
    }

    public void launchIdentifyBreedActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked");

        Intent intent = new Intent(this, IdentifyBreedActivity.class);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);

    }

    public void launchIdentifyDogActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked");
        Intent intent = new Intent(this, IdentifyDogsActivity.class);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);
    }

    public void launchSearchBreedsActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked");
        Intent intent = new Intent(this, SearchDogBreedsActivity.class);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);

    }
}


