package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = MainActivity.class.getSimpleName();



    private TextView difficulty;

    private static String difficulty_level = "You have chosen Beginner Level";

    private static HashMap<String, String[]> imagesMap = new HashMap<>();

    private static boolean gameDifficulty = false;

//    adding all the images in drawable into hashmap
    public void addImages() {
        String[] bullMastiff = new String[]{"bull1", "bull2", "bull3", "bull4", "bull5","bull6","bull7","bull8","bull9","bull10"};
        String[] doberman = new String[]{"doberman1", "doberman2", "doberman3", "doberman4", "doberman5","doberman6","doberman7","doberman8","doberman9","doberman10"};
        String[] eskimo = new String[]{"eskimo1", "eskimo2", "eskimo3", "eskimo4", "eskimo5","eskimo6","eskimo7","eskimo8","eskimo9","eskimo10"};
        String[] germanShepard = new String[]{"german1", "german2", "german3", "german4", "german5","german6","german7","german8","german9","german10"};
        String[] goldenRetriever = new String[]{"golden1", "golden2", "golden3", "golden4", "golden5","golden6","golden7","golden8","golden9","golden10"};
        String[] siberianHusky = new String[]{"husky1", "husky2", "husky3", "husky4", "husky5","husky6","husky7","husky8","husky9","husky10"};
        String[] komondor = new String[]{"komondor1", "komondor2", "komondor3", "komondor4", "komondor5","komondor6","komondor7","komondor8","komondor9","komondor10"};
        String[] labradorRetriever = new String[]{"labrador1", "labrador2", "labrador3", "labrador4", "labrador5","labrador6","labrador7","labrador8","labrador9","labrador10"};
        String[] rottweiler = new String[]{"rotweiller1", "rotweiller2", "rotweiller3", "rotweiller4", "rotweiller5","rotweiller6","rotweiller7","rotweiller8","rotweiller9","rotweiller10"};
        String[] saint = new String[]{"saint1", "saint2", "saint3", "saint4", "saint5","saint6","saint7","saint8","saint9","saint10"};
        String[] saluki = new String[]{"saluki1", "saluki2", "saluki3", "saluki4", "saluki5","saluki6","saluki7","saluki8","saluki9","saluki10"};
        String[] boston = new String[]{"boston1", "boston2", "boston3", "boston4", "boston5","boston6","boston7","boston8","boston9","boston10"};

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
        imagesMap.put("Saluki", saluki);
        imagesMap.put("Boston Bull", boston);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG, "OnCreate");

//        get difficulty text view
        difficulty = (TextView) findViewById(R.id.difficulty_description);

        addImages();
    }
//    activity life cycle

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "OnDestroy");
        Toast.makeText(this, "Application ", Toast.LENGTH_SHORT).show();
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

//    launching the level 01 activity
    public void launchIdentifyBreedActivity(View view) {
        Log.d(LOG_TAG, "Launch Identify Breed Activity");

        Intent intent = new Intent(this, IdentifyBreedActivity.class);
        intent.putExtra("difficulty", gameDifficulty);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);

    }

//    launching the level 02 activity
    public void launchIdentifyDogActivity(View view) {
        Log.d(LOG_TAG, "Launch Identify Dog Activity");
        Intent intent = new Intent(this, IdentifyDogsActivity.class);
        intent.putExtra("difficulty", gameDifficulty);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);
    }

//    launching the level 03 activity
    public void launchSearchBreedsActivity(View view) {
        Log.d(LOG_TAG, "Launch Search Breeds Activity");
        Intent intent = new Intent(this, SearchDogBreedsActivity.class);
        intent.putExtra("Images", imagesMap);
        startActivity(intent);

    }
}


