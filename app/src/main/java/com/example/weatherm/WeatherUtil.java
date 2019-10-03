package com.example.weatherm;

public class WeatherUtil {

    public static String getCelsius(double kelvin) {
        int iKelvin = (int) kelvin;
        return String.valueOf(iKelvin - 273);
    }

}
