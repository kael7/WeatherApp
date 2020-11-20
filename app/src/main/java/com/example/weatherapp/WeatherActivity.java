package com.example.weatherapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            if (Constants.DEBUG) {
                Log.d("WeatherActivity", "Неправильная конфигурация");
            }
            return;
        }

        if (savedInstanceState == null) {
            WeatherFragment details = new WeatherFragment();
            details.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, details).commit();
        }
    }
}