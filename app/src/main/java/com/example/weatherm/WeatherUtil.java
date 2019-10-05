package com.example.weatherm;

public class WeatherUtil {

    public static String getCelsius(double kelvin) {
        int iKelvin = (int) kelvin;
        return String.valueOf(iKelvin - 273);
    }

    public static int getWeatherIcon(int weatherId) {
        // 2xx thunderstorm
        if (weatherId >= 200 && weatherId < 300) {
            return R.drawable.ic_thunderstrom;
        }
        // 3xx drizzle
        else if (weatherId >= 300 && weatherId < 400) {
            return R.drawable.ic_drizzle;
        }
        // 5xx rain
        else if (weatherId >= 500 && weatherId < 600) {
            return R.drawable.ic_rain;
        }
        // 6xx snow
        else if (weatherId >= 600 && weatherId < 700) {
            return R.drawable.ic_rain;
        }
        // 7xx atmosphere
        else if (weatherId >= 700 && weatherId < 800) {
            return R.drawable.ic_rain;
        }
        // 800 clear
        else if (weatherId == 800) {
            return R.drawable.ic_clear;
        }
        // 80x clouds
        else if (weatherId > 800 && weatherId < 810) {
            return R.drawable.ic_clouds;
        }
        // 예외처리
        else {
            return R.drawable.ic_smile;
        }

    }

}
