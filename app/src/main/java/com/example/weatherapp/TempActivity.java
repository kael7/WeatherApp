package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class TempActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_city);

        final TextView textViewTemp = findViewById(R.id.textViewTemp);
        final TextView textViewCity = findViewById(R.id.textViewCity);
        final TextView textViewDate = findViewById(R.id.textViewDate);

        final MainPresenter presenter = MainPresenter.getInstance();
        String temp = ((Integer) presenter.getTemp()).toString();
        String city = ((String) presenter.getCity()).toString();
        String date = ((String) presenter.getDate()).toString();
        textViewTemp.setText(temp);
        textViewCity.setText(city);
        textViewDate.setText(date);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempActivity.this, SettingsActivity.class);
                startActivity(intent);

                textViewTemp.setText(((Integer) presenter.getTemp()).toString());
            }
        });

        Button buttonBrowser = findViewById(R.id.button4);
        buttonBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://yandex.kz/pogoda/" + city);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }
}
