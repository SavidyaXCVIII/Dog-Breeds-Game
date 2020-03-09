package com.coursework.dogsbreeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdentifyDogsActivity extends AppCompatActivity {

    private static final long TIME = 60000;

    private static HashMap<String, String[]> imagesMap;

    private static Random random = new Random();

    private static String breedName;
    private static String breedNameOne;
    private static String breedNameTwo;
    private static String breedNameThree;
    private static String imageNameOne;
    private static String imageNameTwo;
    private static String imageNameThree;
    private ImageView imageViewOne;
    private ImageView imageViewTwo;
    private ImageView imageViewThree;
    private static String selectedImage;
    private TextView breed;
    private TextView result;
    private Button submitButton;
    private TextView countDown;
    private static boolean difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dogs);

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

        if (difficulty){
            countDown.setVisibility(View.VISIBLE);
            startTimer();
        }
        else {
            countDown.setVisibility(View.INVISIBLE);
        }


    }
    public void startTimer(){
        new CountDownTimer(10000, 1000) {

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

    public void setImages() {
        Set<Integer> randomSet = new HashSet<>();

        while (randomSet.size() < 3) {
            int randomKey = random.nextInt(10);
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

    public String getImage(String[] imagesArray) {
        String imageName = null;

        int randValueOne = random.nextInt(5);
        imageName = imagesArray[randValueOne];
        return imageName;
    }

    public void checkAnswer(){
        String resultDescription = null;
        if (selectedImage.equals(breedName)){
            resultDescription = "CORRECT";
            result.setText(resultDescription);
        }
        else{
            resultDescription = "WRONG";
            result.setText(resultDescription);
        }
    }
    public void disableText(){
        countDown.setVisibility(View.INVISIBLE);
    }


    public void checkImageOne(View view) {
        selectedImage = breedNameOne;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

    public void checkImageTwo(View view) {
        selectedImage = breedNameTwo;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

    public void checkImageThree(View view) {
        selectedImage = breedNameThree;
        submitButton.setEnabled(true);
        disableImageClick();
        checkAnswer();
        disableText();
    }

    public void disableImageClick(){
        ImageView breedImageOne = findViewById(R.id.breed_image_1);
        breedImageOne.setEnabled(false);
        ImageView breedImageTwo = findViewById(R.id.breed_image_2);
        breedImageTwo.setEnabled(false);
        ImageView breedImageThree = findViewById(R.id.breed_image_3);
        breedImageThree.setEnabled(false);
    }

    public void next(View view) {
        restartActivity();
    }

    public void restartActivity(){
        Intent identifyDogsBreedActivity = new Intent(this, IdentifyDogsActivity.class);
        finish();
        identifyDogsBreedActivity.putExtra("Images",imagesMap);
        identifyDogsBreedActivity.putExtra("difficulty", difficulty);
        startActivity(identifyDogsBreedActivity);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("imageNameOne", imageNameOne);
        outState.putString("imageNameTwo", imageNameTwo);
        outState.putString("imageNameThree", imageNameThree);
        outState.putString("breedName", breedName);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageNameOne = savedInstanceState.getString("imageNameOne", imageNameOne);
        imageNameTwo = savedInstanceState.getString("imageNameTwo", imageNameTwo);
        imageNameThree = savedInstanceState.getString("imageNameThree", imageNameThree);
        breedName = savedInstanceState.getString("breedName", breedName);

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

    }
}
