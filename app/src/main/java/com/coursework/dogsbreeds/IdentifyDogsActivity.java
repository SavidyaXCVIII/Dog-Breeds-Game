package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class IdentifyDogsActivity extends AppCompatActivity {

    private static HashMap<String, String[]> imagesMap;

    private static String breedNameOne;
    private static String breedNameTwo;
    private static String breedNameThree;

    private static String imageNameOne;
    private static String imageNameTwo;
    private static String imageNameThree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dogs);


    }

    public String getImageNameOne(){
        Random random = new Random();
        int randKey1 = random.nextInt(3) ;
        Set<String> keys = imagesMap.keySet();
        String[] breedNamesArray = keys.toArray(new String[keys.size()]);
        breedNameOne = breedNamesArray[randKey1];

        String[] imageNamesArrayOne = imagesMap.get(breedNamesArray[randKey1]);

        int randValueOne = random.nextInt(5);
        if (imageNamesArrayOne[randValueOne] == null){
            getImageNameOne();
        }
        imageNameOne = imageNamesArrayOne[randValueOne];
        imageNamesArrayOne[randValueOne] = null;
        imagesMap.put(breedNameOne, imageNamesArrayOne);
        return imageNameOne;
    }



}
