package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_city);

        final TextView textViewTemp = findViewById(R.id.textViewTemp);
        final TextView textViewCity = findViewById(R.id.textViewCity);
        final MainPresenter presenter = MainPresenter.getInstance();
        textViewTemp.setText(((Integer)presenter.getTemp()).toString());
        textViewCity.setText(((String)presenter.getCity()).toString());

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempActivity.this, SettingsActivity.class);
                startActivity(intent);

                textViewTemp.setText(((Integer)presenter.getTemp()).toString());
            }
        });
    }
}
