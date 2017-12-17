package com.mwysocka.italiano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        LinearLayout numbers = (LinearLayout) findViewById(R.id.numbers);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        // Find the View that shows the family category
        LinearLayout family = (LinearLayout) findViewById(R.id.family);

        // Set a click listener on that View
        family.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);

                // Start the new activity
                startActivity(familyIntent);
            }
        });

        // Find the View that shows the colors category
        LinearLayout colors = (LinearLayout) findViewById(R.id.colors);

        // Set a click listener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ColorsActivity}
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);

                // Start the new activity
                startActivity(colorsIntent);
            }
        });

        // Find the View that shows the phrases category
        LinearLayout phrases = (LinearLayout) findViewById(R.id.phrases);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });

        // Find the View that shows the food category
        LinearLayout food = (LinearLayout) findViewById(R.id.food);

        // Set a click listener on that View
        food.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FoodActivity}
                Intent foodIntent = new Intent(MainActivity.this, FoodActivity.class);

                // Start the new activity
                startActivity(foodIntent);
            }
        });

        // Find the View that shows the calendar category
        LinearLayout calendar = (LinearLayout) findViewById(R.id.calendar);

        // Set a click listener on that View
        calendar.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link CalendarActivity}
                Intent calendarIntent = new Intent(MainActivity.this, CalendarActivity.class);

                // Start the new activity
                startActivity(calendarIntent);
            }
        });

        // Find the View that shows the weather category
        LinearLayout weather = (LinearLayout) findViewById(R.id.weather);

        // Set a click listener on that View
        weather.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link WeatherActivity}
                Intent weatherIntent = new Intent(MainActivity.this, WeatherActivity.class);

                // Start the new activity
                startActivity(weatherIntent);
            }
        });

        // Find the View that shows the words category
        LinearLayout words = (LinearLayout) findViewById(R.id.words);

        // Set a click listener on that View
        words.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link WordsActivity}
                Intent wordsIntent = new Intent(MainActivity.this, WordsActivity.class);

                // Start the new activity
                startActivity(wordsIntent);
            }
        });


        // Find the View that shows the calendar category
        LinearLayout house = (LinearLayout) findViewById(R.id.house);

        // Set a click listener on that View
        house.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link HouseActivity}
                Intent houseIntent = new Intent(MainActivity.this, HouseActivity.class);

                // Start the new activity
                startActivity(houseIntent);
            }
        });
    }
}
