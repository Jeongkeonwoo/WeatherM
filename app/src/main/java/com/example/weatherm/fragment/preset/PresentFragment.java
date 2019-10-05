package com.example.weatherm.fragment.preset;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherm.Data;
import com.example.weatherm.R;
import com.example.weatherm.WeatherData;
import com.example.weatherm.WeatherManager;
import com.example.weatherm.WeatherUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PresentFragment extends Fragment {

    private RecyclerView recyclerView;
    private PresentAdapter presentAdapter;
    private  final int WEATHER = 24;
    private ProgressDialog pd;//view 에서 다른 view 로 넘어갈때 로딩
    private WeatherManager weatherManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_present, null);
        //TextView textView = view.findViewById(R.id.present_weather_temperature);
        recyclerView = view.findViewById(R.id.present_recyclerview);

        ArrayList<Data> data = new ArrayList<>();

        for (int i = 0; i < WEATHER; i++ ){
            data.add(new Data(R.mipmap.ic_launcher, i+1+""));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        presentAdapter = new PresentAdapter();

        presentAdapter.setData(data);

        recyclerView.setAdapter(presentAdapter);


//        showProgress("날씨 요청 중입니다.");

//        weatherManager = new WeatherManager(this, new WeatherManager.OnChangeWeather() {
//            @Override
//            public void change(WeatherData weatherData) {
//                hideProgress();
//
//                String temp = WeatherUtil.getCelsius(weatherData.getMain().getTemp());
//                //   tvTemp.setText(temp);
//            }
//        });

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        return view;
    }

    public void showProgress(String message) {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }

        //pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    public void hideProgress() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }
}
