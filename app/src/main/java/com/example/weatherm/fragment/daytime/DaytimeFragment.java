package com.example.weatherm.fragment.daytime;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherm.MainActivity;
import com.example.weatherm.WeatherManager;
import com.example.weatherm.R;
import com.example.weatherm.data.ForecastData;

import java.util.ArrayList;
import java.util.List;

public class DaytimeFragment extends Fragment {

    private MainActivity activity;

    private RecyclerView recyclerView;
    private DaytimeAdapter daytimeAdapter;

//    private WeatherManager weatherManager;
    // 3시간별로 출력할 날씨 개수
    private final int WEATHER_COUNT = 10;

    private ForecastData forecastData;

    //일주일 별로 출력할 날씨 개수
    private final int day = 7;

    //context 를 매개변수로 받아 context 를 MainActivity 타입으로 강제 형변환.?
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //view 를 객체화 시킴
        View view = inflater.inflate(R.layout.fragment_daytime, null);
        //TextView textView = view.findViewById(R.id.text);
        recyclerView = view.findViewById(R.id.daytime_recyclerview);

//        weatherManager = new WeatherManager(activity, new WeatherManager.OnChangeWeather() {
//            @Override
//            public void change(WeatherData weatherData, ForecastData forecastData) {
//                //api 응답 완료시 실행.
//                activity.hideProgress();
//                loadWeekly(forecastData);
//            }
//        });
        forecastData = activity.getForecastData();

        if (forecastData != null) {
           loadWeekly();
        }
//        activity.hideProgress();
//        loadWeekly(forecastData);
        return view;
    }

    private void loadWeekly() {

        List<ForecastData.ListBean> forecastList = new ArrayList<>();

        for (int i = 0; i < WEATHER_COUNT; i++) {
            forecastList.add(forecastData.getList().get(i));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        daytimeAdapter = new DaytimeAdapter(forecastList);

        //daytimeAdapter.setDataList(data);

        recyclerView.setAdapter(daytimeAdapter);
    }

}
