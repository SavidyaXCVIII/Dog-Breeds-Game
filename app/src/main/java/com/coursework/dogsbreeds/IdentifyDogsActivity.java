package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdentifyDogsActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dogs);

        Intent intent = getIntent();
        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");

        setImages();

        breed = (TextView) findViewById(R.id.breed_name);
        breed.setText(breedName);

        result = (TextView) findViewById(R.id.result);


        ImageView breedImageOne = findViewById(R.id.breed_image_1);
        int resource_id_one = getResources().getIdentifier(imageNameOne, "drawable", "com.coursework.dogsbreeds");
        breedImageOne.setImageResource(resource_id_one);

        ImageView breedImageTwo = findViewById(R.id.breed_image_2);
        int resource_id_two = getResources().getIdentifier(imageNameTwo, "drawable", "com.coursework.dogsbreeds");
        breedImageTwo.setImageResource(resource_id_two);

        ImageView breedImageThree = findViewById(R.id.breed_image_3);
        int resource_id_three = getResources().getIdentifier(imageNameThree, "drawable", "com.coursework.dogsbreeds");
        breedImageThree.setImageResource(resource_id_three);

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
        System.out.println(imageNameOne);

        String[] imageNamesArrayTwo = imagesMap.get(breedNamesArray[randomValues[1]]);
        imageNameTwo = getImage(imageNamesArrayTwo);
        System.out.println(imageNameTwo);

        String[] imageNamesArrayThree = imagesMap.get(breedNamesArray[randomValues[2]]);
        imageNameThree = getImage(imageNamesArrayThree);
        System.out.println(imageNameThree);

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


    public void checkImageOne(View view) {
        selectedImage = breedNameOne;
        disableImageClick();
        checkAnswer();
    }

    public void checkImageTwo(View view) {
        selectedImage = breedNameTwo;
        disableImageClick();
        checkAnswer();
    }

    public void checkImageThree(View view) {
        selectedImage = breedNameThree;
        disableImageClick();
        checkAnswer();
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
        startActivity(identifyDogsBreedActivity);
    }
}
