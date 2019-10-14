package com.example.weatherm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherUtil {

    // kelvin 온도를 celsius 온도로 변환
    public static String getCelsius(double kelvin) {
        int iKelvin = (int) kelvin;
        return String.valueOf(iKelvin - 273);
    }

    // weatherId에 따라 icon 리턴
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

    // ex) 오후 11시
    public static String getHour(String strDate) {
        String result = "";

        SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = formatYMDHMS.parse(strDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 9);

            Date koreaDate = new Date();
            koreaDate.setTime(calendar.getTimeInMillis());

            SimpleDateFormat formatH = new SimpleDateFormat("a hh");

            result = formatH.format(koreaDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

}
