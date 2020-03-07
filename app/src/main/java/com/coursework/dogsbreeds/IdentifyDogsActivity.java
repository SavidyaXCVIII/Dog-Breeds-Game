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
    private static String imageNameOne;
    private static String imageNameTwo;
    private static String imageNameThree;
    private static String bredNameText;
    private TextView breed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dogs);

        Intent intent = getIntent();
        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");

        setImages();

        breed = (TextView)findViewById(R.id.breed_name);
        breed.setText(breedName);

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

    public void setImages(){
        Set<Integer> randomSet = new HashSet<>();

        while (randomSet.size() < 3){
            int randomKey = random.nextInt(10);
            randomSet.add(randomKey);
        }
        Integer[] randomValues = randomSet.toArray(new Integer[randomSet.size()]);


        Set<String> keys = imagesMap.keySet();
        String[] breedNamesArray = keys.toArray(new String[keys.size()]);

        String[] breedArray = new String[]{breedNamesArray[randomValues[0]],breedNamesArray[randomValues[1]],breedNamesArray[randomValues[2]]};

        breedName = breedArray[random.nextInt(3)];
        System.out.println(breedNamesArray[randomValues[0]]);
        System.out.println(breedNamesArray[randomValues[1]]);
        System.out.println(breedNamesArray[randomValues[2]]);

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

    public String getImage(String[] imagesArray){
        String imageName = null;

        int randValueOne = random.nextInt(5);
        imageName = imagesArray[randValueOne];
        imagesArray[randValueOne] = null;
        imagesMap.put(breedName, imagesArray);
        if (imageName == null){
            return getImage(imagesArray);
        }
        return imageName;
    }

    public void next(View view) {
        restartActivity();
    }

    public void restartActivity(){
        Intent intent = new Intent();
        finish();
        startActivity(intent);
    }

//    public String getImageNameTwo(){
//        Random random = new Random();
//        int randKey1 = random.nextInt((6-3) + 1) + 3  ;
//        Set<String> keys = imagesMap.keySet();
//        String[] breedNamesArray = keys.toArray(new String[keys.size()]);
//        breedNameTwo = breedNamesArray[randKey1];
//
//        String[] imageNamesArrayOne = imagesMap.get(breedNamesArray[randKey1]);
//
//        int randValueOne = random.nextInt(5);
//        if (imageNamesArrayOne[randValueOne] == null){
//            getImageNameTwo();
//        }
//        imageNameTwo = imageNamesArrayOne[randValueOne];
//        imageNamesArrayOne[randValueOne] = null;
//        imagesMap.put(breedNameTwo, imageNamesArrayOne);
//        return imageNameTwo;
//    }
//    public String getImageNameThree(){
//        Random random = new Random();
//        int randKey1 = random.nextInt((10-6) + 1) + 6  ;
//        Set<String> keys = imagesMap.keySet();
//        String[] breedNamesArray = keys.toArray(new String[keys.size()]);
//        breedNameThree = breedNamesArray[randKey1];
//
//        String[] imageNamesArrayOne = imagesMap.get(breedNamesArray[randKey1]);
//
//        int randValueOne = random.nextInt(5);
//        if (imageNamesArrayOne[randValueOne] == null){
//            getImageNameThree();
//        }
//        imageNameThree = imageNamesArrayOne[randValueOne];
//        imageNamesArrayOne[randValueOne] = null;
//        imagesMap.put(breedNameThree, imageNamesArrayOne);
//        return imageNameThree;
//    }
//     public String setRandomBreedName(){
//        String[] breedsArray = new String[]{breedNameOne, breedNameTwo, breedNameThree};
//        Random random =  new Random();
//        int rand = random.nextInt(3);
//        breedNameText =
//     }



}
