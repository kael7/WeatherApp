package com.example.weatherapp;

import android.widget.EditText;

import java.util.Random;

public final class MainPresenter {
    private static MainPresenter instance = null;

    private static final Object object = new Object();

    private int temp;
    private String city;

    private MainPresenter(){
        temp = 0;
        city = null;
    }

    public synchronized int randomTemp(){
        Random r = new Random();
        temp = r.nextInt(40 - 5) + 5;
        return temp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public synchronized int getTemp() {
        return temp;
    }

    public synchronized String getCity() {
        return city;
    }

    public static MainPresenter getInstance() {
        synchronized (object){
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }

}
