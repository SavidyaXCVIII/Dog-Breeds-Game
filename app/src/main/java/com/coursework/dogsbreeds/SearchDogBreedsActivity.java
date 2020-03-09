package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class SearchDogBreedsActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static HashMap<String, String[]> imagesMap;

    private ViewPager viewPager;

    private AutoCompleteTextView autoCompleteTextView;

    private static String[] breeds;

    private static boolean stopped = false;

    private static Timer timer;

    private static Integer[] imageId = null;

    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dog_breeds);

        viewPager = (ViewPager) findViewById(R.id.image_slider);

        Intent intent = getIntent();
        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");

        Set<String> keys = imagesMap.keySet();
        breeds = keys.toArray(new String[keys.size()]);

        autoCompleteTextView = findViewById(R.id.search);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, breeds);
        autoCompleteTextView.setAdapter(arrayAdapter);

    }
    public String getText(){
        return autoCompleteTextView.getText().toString();
    }

    public void submit(View view) {
        Random random = new Random();
        stopped = false;
        String name = getText();
        String[] imageNamesArray = imagesMap.get(name);

        if (imageNamesArray != null){
            for (int i = 0; i < imageNamesArray.length; i++) {
                int randomPosition = random.nextInt(imageNamesArray.length);
                String temp = imageNamesArray[i];
                imageNamesArray[i] = imageNamesArray[randomPosition];
                imageNamesArray[randomPosition] = temp;
            }

            imageId = new Integer[imageNamesArray.length];
            int count = 0;
            for (String item :
                    imageNamesArray) {
                imageId[count] = getResources().getIdentifier(item, "drawable", "com.coursework.dogsbreeds");
                count++;
            }

            ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this, imageId);
            viewPager.setAdapter(viewPageAdapter);

            timer = new Timer();
            timer.scheduleAtFixedRate(new SlideTimer(), 5000,5000);

            disableSubmitButtonClick(false);
        }

    }

    public void disableSubmitButtonClick(boolean value){
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setEnabled(value);
    }

    public void disableStopButtonClick(boolean value){
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setEnabled(value);
    }


    public void stop(View view) {
        stopped = true;
        if (timer != null){
            timer.cancel();
        }
        disableSubmitButtonClick(true);
    }

    public class SlideTimer extends TimerTask{

        @Override
        public void run() {

            SearchDogBreedsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (stopped){
                        Log.d(LOG_TAG, "Slider stopped");
                    }
                    else {
                        if (count != imageId.length) {
                            if (viewPager.getCurrentItem() == count){
                                viewPager.setCurrentItem(count + 1);
                            }
                            count++;
                        }
                    }

                }
            });

        }
    }
}
