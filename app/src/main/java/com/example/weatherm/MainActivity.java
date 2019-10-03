package com.example.weatherm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    private ProgressDialog pd;

    private WeatherManager weatherManager;

    private TextView tvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemp = findViewById(R.id.tvTemp);

        showProgress("날씨 요청 중입니다.");
        weatherManager = new WeatherManager(this, new WeatherManager.OnChangeWeather() {
            @Override
            public void change(WeatherData weatherData) {
                hideProgress();

                String temp = WeatherUtil.getCelsius(weatherData.getMain().getTemp());
                tvTemp.setText(temp);
            }
        });

    }


    public void showProgress(String message) {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }

        pd = new ProgressDialog(this);
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
