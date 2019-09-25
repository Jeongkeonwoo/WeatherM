package com.example.weatherm.api_retrofit;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiManager {

    private final String APP_ID = "10465524bfe7eaa71c4010cb81b84a11";
    private WeatherService weatherService;

    private static ApiManager apiManager = new ApiManager();

    // Singleton
    public static ApiManager getInstance() {
        return apiManager;
    }

    private ApiManager() {

//        https://api.openweathermap.org/data/2.5/weather?q=Seoul&APPID=10465524bfe7eaa71c4010cb81b84a11

        // log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(ScalarsConverterFactory.create())  // response String 으로 볼 때 사용
//                .addConverterFactory(GsonConverterFactory.create())  // response Gson으로 변환
                .client(client)
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    public Call<String> getWeather(String city) {
        Map<String, String> map = new HashMap<>();
        map.put("APPID", APP_ID);
        map.put("q", city);

        return weatherService.getWeather(map);
    }
}
