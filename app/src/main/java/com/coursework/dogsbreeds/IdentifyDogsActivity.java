package com.coursework.dogsbreeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdentifyDogsActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static HashMap<String, String[]> imagesMap;

    private static ArrayList<String> usedArray = new ArrayList<>();

    private static Random random = new Random();

    private static String breedName;
    private static String breedNameOne;
    private static String breedNameTwo;
    private static String breedNameThree;
    private static String imageNameOne;
    private static String imageNameTwo;
    private static String imageNameThree;
    private static String selectedImage;
    private static long time;
    private static CountDownTimer countDownTimer;
    private TextView breed;
    private TextView result;
    private Button submitButton;
    private TextView countDown;
    private static boolean difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dogs);
        Log.i(LOG_TAG, "OnCreate");

//        load the data from the previous activity
        Intent intent = getIntent();
        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");
        difficulty = getIntent().getExtras().getBoolean("difficulty");

        setImages();

        breed = (TextView) findViewById(R.id.breed_name);
        breed.setText(breedName);

        result = (TextView) findViewById(R.id.result);

        submitButton = (Button) findViewById(R.id.next);
        submitButton.setEnabled(false);

        countDown = (TextView) findViewById(R.id.countdown);

        ImageView breedImageOne = findViewById(R.id.breed_image_1);
        int resource_id_one = getResources().getIdentifier(imageNameOne, "drawable", "com.coursework.dogsbreeds");
        breedImageOne.setImageResource(resource_id_one);

        ImageView breedImageTwo = findViewById(R.id.breed_image_2);
        int resource_id_two = getResources().getIdentifier(imageNameTwo, "drawable", "com.coursework.dogsbreeds");
        breedImageTwo.setImageResource(resource_id_two);

        ImageView breedImageThree = findViewById(R.id.breed_image_3);
        int resource_id_three = getResources().getIdentifier(imageNameThree, "drawable", "com.coursework.dogsbreeds");
        breedImageThree.setImageResource(resource_id_three);

//        Enabling the time if the user chooses intermediate difficulty
        if (difficulty) {
            countDown.setVisibility(View.VISIBLE);
            startTimer();
        } else {
            countDown.setVisibility(View.INVISIBLE);
        }


    }

    //        creating the countdown object and start the counter
    //    reference - https://developer.android.com/reference/android/os/CountDownTimer
    public void startTimer() {
        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished / 1000;
                countDown.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDown.setText("TIMED OUT!");
                submitButton.setEnabled(true);
                disableImageClick();
            }
        }.start();
    }

    //    get random 3 images and display
    public void setImages() {
        Set<Integer> randomSet = new HashSet<>();

        while (randomSet.size() < 3) {
            int randomKey = random.nextInt(12);
            randomSet.add(randomKey);
        }

        Integer[] randomValues = randomSet.toArray(new Integer[randomSet.size()]);

        Set<String> keys = imagesMap.keySet();
        String[] breedNamesArray = keys.toArray(new String[keys.size()]);

        String[] breedArray = new String[]{breedNamesArray[randomValues[0]], breedNamesArray[randomValues[1]], breedNamesArray[randomValues[2]]};

        breedName = breedArray[random.nextInt(3)];
        breedNameOne = breedArray[0];
        breedNameTwo = breedArray[1];
        breedNameThree = breedArray[2];

        String[] imageNamesArrayOne = imagesMap.get(breedNamesArray[randomValues[0]]);
        imageNameOne = getImage(imageNamesArrayOne);

        String[] imageNamesArrayTwo = imagesMap.get(breedNamesArray[randomValues[1]]);
        imageNameTwo = getImage(imageNamesArrayTwo);

        String[] imageNamesArrayThree = imagesMap.get(breedNamesArray[randomValues[2]]);
        imageNameThree = getImage(imageNamesArrayThree);

    }

    //    get a random image
    public String getImage(String[] imagesArray) {
        String imageName = null;

//        check if the image is used before
        do {
            int randValueOne = random.nextInt(10);
            imageName = imagesArray[randValueOne];

        } while (usedArray.contains(imageName));

        usedArray.add(imageName);


        return imageName;
    }

//    check the answer and display result according to the user input
    public void checkAnswer() {
        String resultDescription = null;
        if (selectedImage.equals(breedName)) {
            resultDescription = "CORRECT";
            result.setText(resultDescription);
        } else {
            resultDescription = "WRONG";
            result.setText(resultDescription);
        }
    }

    public void disableText() {
        countDown.setVisibility(View.INVISIBLE);
    }


//    check answer on click event
    public void checkImageOne(View view) {
        selectedImage = breedNameOne;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

//    check answer on click event
    public void checkImageTwo(View view) {
        selectedImage = breedNameTwo;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

//    check answer on click event
    public void checkImageThree(View view) {
        selectedImage = breedNameThree;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

//    disable image click after one lick event
    public void disableImageClick() {
        ImageView breedImageOne = findViewById(R.id.breed_image_1);
        breedImageOne.setEnabled(false);
        ImageView breedImageTwo = findViewById(R.id.breed_image_2);
        breedImageTwo.setEnabled(false);
        ImageView breedImageThree = findViewById(R.id.breed_image_3);
        breedImageThree.setEnabled(false);
    }

//    restart activity when the user clicks next
    public void next(View view) {
        restartActivity();
    }

//    send data to the next activity before restarting
    public void restartActivity() {
        Intent identifyDogsBreedActivity = new Intent(this, IdentifyDogsActivity.class);
        finish();
        identifyDogsBreedActivity.putExtra("Images", imagesMap);
        identifyDogsBreedActivity.putExtra("difficulty", difficulty);
        startActivity(identifyDogsBreedActivity);
    }

//    saving the variable before rotating
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("imageNameOne", imageNameOne);
        outState.putString("imageNameTwo", imageNameTwo);
        outState.putString("imageNameThree", imageNameThree);
        outState.putString("breedName", breedName);
        outState.putString("selectedImage", selectedImage);
        outState.putLong("remainingTime", time);
    }

//    getting the variables data after rotating
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageNameOne = savedInstanceState.getString("imageNameOne", imageNameOne);
        imageNameTwo = savedInstanceState.getString("imageNameTwo", imageNameTwo);
        imageNameThree = savedInstanceState.getString("imageNameThree", imageNameThree);
        breedName = savedInstanceState.getString("breedName", breedName);
        selectedImage = savedInstanceState.getString("selectedName", selectedImage);
        String selectedImageAfter = selectedImage;
        long timeAfter = savedInstanceState.getLong("remainingTime", time);

        if (difficulty){
            countDownTimer.cancel();

            timeAfter = timeAfter * 1000;


//        resume the counter if the user rotate the device
            new CountDownTimer(timeAfter, 1000) {

                public void onTick(long millisUntilFinished) {
                    countDown.setText("Seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    countDown.setText("TIMED OUT!");
                    submitButton.setEnabled(true);
                    disableImageClick();
                }
            }.start();
        }


//        setting the same images if the user rotate the device
        ImageView breedImageOne = findViewById(R.id.breed_image_1);
        int resource_id_one = getResources().getIdentifier(imageNameOne, "drawable", "com.coursework.dogsbreeds");
        breedImageOne.setImageResource(resource_id_one);

        ImageView breedImageTwo = findViewById(R.id.breed_image_2);
        int resource_id_two = getResources().getIdentifier(imageNameTwo, "drawable", "com.coursework.dogsbreeds");
        breedImageTwo.setImageResource(resource_id_two);

        ImageView breedImageThree = findViewById(R.id.breed_image_3);
        int resource_id_three = getResources().getIdentifier(imageNameThree, "drawable", "com.coursework.dogsbreeds");
        breedImageThree.setImageResource(resource_id_three);

        breed = (TextView) findViewById(R.id.breed_name);
        breed.setText(breedName);

        System.out.println(selectedImage);

//        set the result if the user rotate the device after clicking submit button
        if (selectedImageAfter != null) {
            submitButton.setEnabled(true);
            disableImageClick();
            checkAnswer();
            disableText();
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
