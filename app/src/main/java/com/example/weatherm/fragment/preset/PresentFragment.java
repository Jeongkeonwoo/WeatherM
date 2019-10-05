package com.example.weatherm.fragment.preset;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherm.Data;
import com.example.weatherm.MainActivity;
import com.example.weatherm.R;
import com.example.weatherm.WeatherData;
import com.example.weatherm.WeatherManager;
import com.example.weatherm.WeatherUtil;

import java.util.ArrayList;

public class PresentFragment extends Fragment {

    private MainActivity activity;

    private RecyclerView recyclerView;
    private TextView presentWeatherTemperature;
    private TextView presentWeatherCity;
    private ImageView presentWeatherIcon;

    private PresentAdapter presentAdapter;
    private final int WEATHER = 24;
    private WeatherManager weatherManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_present, null);

        presentWeatherTemperature = view.findViewById(R.id.present_weather_temperature);
        presentWeatherCity = view.findViewById(R.id.present_weather_city);
        presentWeatherIcon = view.findViewById(R.id.present_weather_icon);
        recyclerView = view.findViewById(R.id.present_recyclerview);

        ArrayList<Data> data = new ArrayList<>();

        for (int i = 0; i < WEATHER; i++) {
            data.add(new Data(R.mipmap.ic_launcher, i + 1 + ""));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        presentAdapter = new PresentAdapter();

        presentAdapter.setData(data);

        recyclerView.setAdapter(presentAdapter);


        // api 요청
        activity.showProgress("날씨 요청 중입니다.");

        weatherManager = new WeatherManager(activity, new WeatherManager.OnChangeWeather() {
            @Override
            public void change(WeatherData weatherData) {
                // api 응답 완료시 실행
                activity.hideProgress();
               load(weatherData);
            }
        });

        return view;
    }

    private void load(WeatherData weatherData) {

        // 현재 도시 이름
        presentWeatherCity.setText(weatherData.getName());

        // 현재 날씨 이미지
        int weatherId = weatherData.getWeather().get(0).getId();
        int imageResource = WeatherUtil.getWeatherIcon(weatherId);
        presentWeatherIcon.setImageResource(imageResource);

        // 현재 온도
        String temp = WeatherUtil.getCelsius(weatherData.getMain().getTemp());
        presentWeatherTemperature.setText(temp + "˚");
    }
}
