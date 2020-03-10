package com.coursework.dogsbreeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static ArrayList<String> usedArray = new ArrayList<>();

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static HashMap<String, String[]> imagesMap;

    private static String imageName;

    private static String breedName;

    private static String spinnerLabel;

    private static String button = "Submit";

    private static String answerDescription;

    private static boolean difficulty;

    private static long time;

    private static CountDownTimer countDownTimer;

    private TextView countdown;

    private TextView answer;

    private TextView correctAnswer;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        Log.i(LOG_TAG, "OnCreate");

//        load the data from the previous activity
        Intent intent = getIntent();
        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");
        difficulty = getIntent().getExtras().getBoolean("difficulty");

        ImageView breedImage = findViewById(R.id.breed_image);


//        get a random image
        imageName = getImageName();

//        set image
        int resource_id = getResources().getIdentifier(imageName, "drawable", "com.coursework.dogsbreeds");
        breedImage.setImageResource(resource_id);

        Spinner spinner = findViewById(R.id.dog_names_spinner);

        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dog_names_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        answer = (TextView) findViewById(R.id.result);
        correctAnswer = (TextView) findViewById((R.id.result_correct_answer));
        submitButton = (Button) findViewById(R.id.submit_button);
        countdown = (TextView) findViewById(R.id.countdown_level_01);

//        Enabling the time if the user chooses intermediate difficulty
        if (difficulty) {
            countdown.setVisibility(View.VISIBLE);
            setTimer();
        } else {
            countdown.setVisibility(View.INVISIBLE);
        }
    }


//    creating the countdown object and start the counter
//    reference - https://developer.android.com/reference/android/os/CountDownTimer

    public final void setTimer() {
        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
//                assigning the remain time to a variable
                time = millisUntilFinished / 1000;
                countdown.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countdown.setText("TIMED OUT!");
                if (button.equals("Submit")) {
                    submit();
                } else {
                    check();
                    hideCountDown();
                }
            }
        }.start();
    }



//    method to hide countdown
    public void hideCountDown() {
        countdown.setVisibility(View.INVISIBLE);
    }

//    method to get a random image
    public String getImageName() {
        String imageName = null;
        Random random = new Random();

//        check if the image is used before
        do {
            int randKey = random.nextInt(12);

            Set<String> keys = imagesMap.keySet();
            String[] breedNamesArray = keys.toArray(new String[keys.size()]);
            breedName = breedNamesArray[randKey];
            String[] imageNamesArray = imagesMap.get(breedNamesArray[randKey]);
            int randValue = random.nextInt(10);
            imageName = imageNamesArray[randValue];

        } while (usedArray.contains(imageName));

//        adding the random image to the used array
        usedArray.add(imageName);
        return imageName;
    }

//    slide show
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

//    restarting the activity on next
    public void restartActivity() {
        Intent identifyDogBreedActivity = new Intent(this, IdentifyBreedActivity.class);
        finish();
        identifyDogBreedActivity.putExtra("Images", imagesMap);
        identifyDogBreedActivity.putExtra("difficulty", difficulty);
        startActivity(identifyDogBreedActivity);
    }

//    check answer when the user click the submit button
    public void checkAnswer(View view) {
        hideCountDown();
        submit();
    }

//    checking the user input after rotation
    public void check() {
        if (spinnerLabel.equals(breedName)) {
            answerDescription = "Your answer is CORRECT!";
            answer.setTextColor(this.getResources().getColor(R.color.ColorGreen));
        } else {
            answerDescription = "Your answer is WRONG";
            answer.setTextColor(this.getResources().getColor(R.color.ColorRed));
            correctAnswer.setTextColor(this.getResources().getColor(R.color.colorBlue));
            correctAnswer.setText("Answer: " + breedName);
        }
        answer.setText(answerDescription);

    }

//    checking the user input before rotation
    public void submit() {
        if (button.equals("Submit")) {
            button = "Next";
            submitButton.setText(button);

            if (spinnerLabel.equals(breedName)) {
                answerDescription = "Your answer is CORRECT!";
                answer.setTextColor(this.getResources().getColor(R.color.ColorGreen));
            } else {
                answerDescription = "Your answer is WRONG";
                answer.setTextColor(this.getResources().getColor(R.color.ColorRed));
                correctAnswer.setTextColor(this.getResources().getColor(R.color.colorBlue));
                correctAnswer.setText("Answer: " + breedName);
            }

            answer.setText(answerDescription);
        } else {
            if (button.equals("Next")) {
                button = "Submit";
                submitButton.setText(button);
                restartActivity();

            }
        }
    }

//    saving the vriable before rotating
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("image", imageName);
        outState.putString("breed", breedName);
        outState.putString("submit", button);
        outState.putString("description", answerDescription);
        outState.putLong("millisUntilFinished", time);
        outState.putBoolean("difficulty",difficulty);
    }


//    getting the variables data after rotating
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageName = savedInstanceState.getString("image", imageName);
        breedName = savedInstanceState.getString("breed", breedName);
        difficulty = savedInstanceState.getBoolean("difficulty", difficulty);
        long timeAfter = savedInstanceState.getLong("millisUntilFinished", time);
        button = savedInstanceState.getString("submit", button);
        System.out.println(button);
        answerDescription = savedInstanceState.getString("description", answerDescription);

//        resume the counter if the user rotate the device
        if (difficulty){
            countDownTimer.cancel();

            timeAfter = timeAfter * 1000;

            new CountDownTimer(timeAfter, 1000) {


                public void onTick(long millisUntilFinished) {
                    countdown.setText("Seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    countdown.setText("TIMED OUT!");
                    check();
                    button = "Next";
                    submitButton.setText(button);
                    time = 0;

                }
            }.start();
        }

//        setting the same image if the user rotate the device
        ImageView breedImage = findViewById(R.id.breed_image);
        int resource_id = getResources().getIdentifier(imageName, "drawable", "com.coursework.dogsbreeds");
        breedImage.setImageResource(resource_id);

//        set the result if the user rotate the device after clicking submit button
        if (button.equals("Next")) {
            submitButton.setText(button);
            if (answerDescription.equals("Your answer is CORRECT!")) {
                answer.setTextColor(this.getResources().getColor(R.color.ColorGreen));
                answer.setText(answerDescription);
            } else {
                answer.setText(answerDescription);
                answer.setTextColor(this.getResources().getColor(R.color.ColorRed));
                correctAnswer.setTextColor(this.getResources().getColor(R.color.colorBlue));
                correctAnswer.setText("Answer: " + breedName);
            }
        }


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
    }
}
