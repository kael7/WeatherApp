package com.example.weatherapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Фрагмент выбора города из списка
public class CitiesFragment extends Fragment {

    private boolean isExist;

    private City currentCity;

    // При создании фрагмента укажем его макет
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

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом погоду в другом фрагменте
        isExist = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getParcelable(Constants.CITY_KEY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.cities)[0], getResources().getStringArray(R.array.week_days)[0], getResources().getStringArray(R.array.temperatures)[0]);
        }

        // Если можно нарисовать рядом погоду, то сделаем это
        if (isExist) {
            showWeather(currentCity);
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.CITY_KEY, currentCity);
    }

    // создаем список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        String[] cities = getResources().getStringArray(R.array.cities);
        String[] week_days = getResources().getStringArray(R.array.week_days);
        String[] temperatures = getResources().getStringArray(R.array.temperatures);

        final LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < cities.length; i++) {
            final int fi = i;
            String city = cities[i];
            String week_day = week_days[i];
            String temperature = temperatures[i];

            View item = inflater.inflate(R.layout.item, layoutView, false);


            TextView tv = item.findViewById(R.id.textView);

            tv.setText(city);


            layoutView.addView(item);


            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentCity = new City(fi, getResources().getStringArray(R.array.cities)[fi], getResources().getStringArray(R.array.week_days)[fi], getResources().getStringArray(R.array.temperatures)[fi]);
                    showWeather(currentCity);
                }
            });
        }
    }

    // Показать герб. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    private void showWeather(City currentCity) {
        if (isExist) {
            WeatherFragment detailsFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.weather);

            if (detailsFragment == null || currentCity.getImageIndex() != detailsFragment.getCurrentCity().getImageIndex()) {
                detailsFragment = WeatherFragment.create(currentCity);
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.weather, detailsFragment);
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
