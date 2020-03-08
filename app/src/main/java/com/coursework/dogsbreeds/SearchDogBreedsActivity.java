package com.coursework.dogsbreeds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class SearchDogBreedsActivity extends AppCompatActivity {

    private static HashMap<String, String[]> imagesMap;

    private ViewPager viewPager;

    private AutoCompleteTextView autoCompleteTextView;

    private static String[] breeds;

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
        String input = autoCompleteTextView.getText().toString();
        System.out.println(input);
        return input;
    }

    public void submit(View view) {

        System.out.println(R.drawable.bull1);
        String name = getText();
        String[] imageNamesArray = imagesMap.get(name);
        Integer[] imageId = new Integer[imageNamesArray.length];
        System.out.println(imageNamesArray.length);

        int count = 0;
        for (String item :
                imageNamesArray) {
            imageId[count] = getResources().getIdentifier(item, "drawable", "com.coursework.dogsbreeds");
            count++;
        }

        for (Integer one :
                imageId) {
            System.out.println(one);
        }

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this, imageId);
        viewPager.setAdapter(viewPageAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SlideTimer(), 5000,5000);
    }

    public class SlideTimer extends TimerTask{

        @Override
        public void run() {

            SearchDogBreedsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }
                    else if (viewPager.getCurrentItem()==1) {
                        viewPager.setCurrentItem(2);
                    }
                    else if (viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }
                    else if (viewPager.getCurrentItem()==3) {
                        viewPager.setCurrentItem(4);
                    }
                    else if (viewPager.getCurrentItem() == 4){
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}
