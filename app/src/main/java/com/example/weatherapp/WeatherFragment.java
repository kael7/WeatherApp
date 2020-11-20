package com.example.weatherapp;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WeatherFragment extends Fragment {

    public static WeatherFragment create(City city) {
        WeatherFragment fragment = new WeatherFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CITY_KEY, city);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Constants.VERBOSE) {
            Log.v("WeatherFragment", "onCreate");
        }
        
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);

        ImageView weatherImageView = layout.findViewById(R.id.imageView);
        TextView cityNameView = layout.findViewById(R.id.textView);
//        TextView temperatureView = layout.findViewById(R.id.textView2);
//        TextView weekDayView = layout.findViewById(R.id.textView3);

        TypedArray imgs = getResources().obtainTypedArray(R.array.weather_imgs);

        City city = getCurrentCity();

        if (city != null) {
            weatherImageView.setImageResource(imgs.getResourceId(city.getImageIndex(), -1));
            cityNameView.setText(city.getCityName());
//            weekDayView.setText(city.getWeekDay());
//            temperatureView.setText(city.getTemperature());
        }

        return layout;
    }

    public City getCurrentCity() {
        Bundle args = getArguments();
        City city = null;
        if (args != null) {
            city = getArguments().getParcelable(Constants.CITY_KEY);
        }
        return city;
    }












}
