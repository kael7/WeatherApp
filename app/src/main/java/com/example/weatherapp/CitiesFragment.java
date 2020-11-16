package com.example.weatherapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CitiesFragment extends Fragment {

    private boolean isExist;

    private City currentCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExist = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getParcelable(Constants.CITY_KEY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.cities)[0], 0);
        }

        if(isExist) {
            showWeather(currentCity);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.CITY_KEY, currentCity);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        String[] cities = getResources().getStringArray(R.array.cities);

        final LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < cities.length; i++) {
            final int fi = i;
            String city = cities[i];

            View item = inflater.inflate(R.layout.item, layoutView, false);

            TextView tv = item.findViewById(R.id.textView);

            tv.setText(city);

            layoutView.addView(item);



            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temperature = 15;
                    currentCity = new City(fi, getResources().getStringArray(R.array.cities)[fi], fi);
                    showWeather(currentCity);
                }
            });
        }
    }

    private void showWeather(City currentCity) {
        if (isExist) {
            WeatherFragment detailsFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.coat_of_arms);

            if (detailsFragment == null || currentCity.getImageIndex() != detailsFragment.getCurrentCity().getImageIndex()) {
                detailsFragment = WeatherFragment.create(currentCity);
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.coat_of_arms, detailsFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), WeatherActivity.class);

            intent.putExtra(Constants.CITY_KEY, currentCity);
            startActivity(intent);
        }
    }
}
