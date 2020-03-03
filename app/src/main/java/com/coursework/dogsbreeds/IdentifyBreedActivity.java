package com.coursework.dogsbreeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class IdentifyBreedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static HashMap<String, String[]> imagesMap;

    private static String imageName;

    private static String breedName;

    private static String spinnerLabel;

    private static String button = "Submit";

    private static String answerDescription;

    private static int clickCount = 0;

    private TextView answer;
    private TextView correctAnswer;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_breed);

        Intent intent = getIntent();

        breedName = intent.getExtras().getString("breed");

        imagesMap = (HashMap<String, String[]>) intent.getSerializableExtra("Images");

        ImageView breedImage = findViewById(R.id.breed_image);

        imageName = getImageName();

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

        answer = (TextView)findViewById(R.id.result);
        correctAnswer = (TextView)findViewById((R.id.result_correct_answer));
        submitButton = (Button)findViewById(R.id.submit_button);

    }

    public String getImageName() {
//        String imageName = null;
        Random random = new Random();

        int randKey = random.nextInt(10);

        Set<String> keys = imagesMap.keySet();
        String[] breedNamesArray = keys.toArray(new String[keys.size()]);
        breedName = breedNamesArray[randKey];
        String[] imageNamesArray = imagesMap.get(breedNamesArray[randKey]);
        int randValue = random.nextInt(5);
        imageName = imageNamesArray[randValue];
        System.out.println(imageName);

        return imageName;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void restartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void checkAnswer(View view) {

        if (button.equals("Submit")) {
            button = "Next";
            submitButton.setText(button);

            if (spinnerLabel.equals(breedName)) {
                answerDescription = "Your answer is CORRECT!";
                answer.setTextColor(this.getResources().getColor(R.color.ColorGreen));
            }
            else {
                answerDescription = "Your answer is WRONG";
                answer.setTextColor(this.getResources().getColor(R.color.ColorRed));
                correctAnswer.setTextColor(this.getResources().getColor(R.color.colorBlue));
                correctAnswer.setText("Answer: " + breedName);
            }

            answer.setText(answerDescription);
        }
        else {
            if (button.equals("Next")) {
                submitButton.setText(button);
                restartActivity();
                button = "Submit";
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("image", imageName);
        outState.putString("breed", breedName);
        outState.putString("submit", button);
        outState.putString("description", answerDescription);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageName = savedInstanceState.getString("image", imageName);
        breedName = savedInstanceState.getString("breed", breedName);
        ImageView breedImage = findViewById(R.id.breed_image);
        int resource_id = getResources().getIdentifier(imageName, "drawable", "com.coursework.dogsbreeds");
        breedImage.setImageResource(resource_id);
        button = savedInstanceState.getString("submit", button);
        answerDescription = savedInstanceState.getString("description", answerDescription);
        if (button.equals("Next")){
            submitButton.setText(button);
            if (answerDescription.equals("Your answer is CORRECT!")){
                answer.setTextColor(this.getResources().getColor(R.color.ColorGreen));
                answer.setText(answerDescription);
            }
            else {
                answer.setText(answerDescription);
                answer.setTextColor(this.getResources().getColor(R.color.ColorRed));
                correctAnswer.setTextColor(this.getResources().getColor(R.color.colorBlue));
                correctAnswer.setText("Answer: " + breedName);
            }
        }

    }
}
