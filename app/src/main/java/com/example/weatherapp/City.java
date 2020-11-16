package com.example.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private final int imageIndex;
    private final String cityName;
    private final int temperature;

    protected City(Parcel in) {
        imageIndex = in.readInt();
        cityName = in.readString();
        temperature = in.readInt();
    }

    public City(int imageIndex, String cityName, int temperature) {
        this.imageIndex = imageIndex;
        this.cityName = cityName;
        this.temperature = temperature;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageIndex);
        dest.writeString(cityName);
        dest.writeInt(temperature);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTemperature() {
        return temperature;
    }
}
