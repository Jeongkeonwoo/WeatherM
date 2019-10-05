package com.example.weatherm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.weatherm.api_okhttp.HttpConnection;
import com.example.weatherm.api_retrofit.ApiManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherManager {

    private Activity activity;
    private OnChangeWeather onChangeWeather;

    // 날씨
    private ApiManager apiManager;

    public WeatherManager(Activity activity, OnChangeWeather onChangeWeather) {
        this.activity = activity;
        this.onChangeWeather = onChangeWeather;

        apiManager = ApiManager.getInstance();

        // 위치 확인
        final LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한 없으면 메소드 실행 중지
            return;
        }

        // 5분마다 or 100m 마다 위치 정보 갱신
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, mLocationListener, null);

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                30000, 100, mLocationListener);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                30000, 100, mLocationListener);
    }

    private final LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();  // 위도
            double longitude = location.getLongitude(); // 경도

            String lat = String.format("%.2f", latitude);
            String lon = String.format("%.2f", longitude);

            requestWeather(lat, lon);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void requestWeather(String lat, String lon) {

        // OkHttp의 Call 이랑 클래스명이 겹쳐서 retrofit2.Call 을 써준것

        // Seoul의 날씨 요청
//        retrofit2.Call<String> response = apiManager.getWeather("Seoul");

        // 위도 경도로 날씨 요청
        retrofit2.Call<WeatherData> response = apiManager.getWeatherByLatitude(lat, lon);

        response.enqueue(new retrofit2.Callback<WeatherData>() {
            // 응답 성공
            @Override
            public void onResponse(retrofit2.Call<WeatherData> call, retrofit2.Response<WeatherData> response) {
                Log.d("Retrofit", "onResponse");
                onChangeWeather.change(response.body());
            }

            // 응답 실패
            @Override
            public void onFailure(retrofit2.Call<WeatherData> call, Throwable t) {
                Log.d("Retrofit", "onFailure");
                Toast.makeText(activity, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show();

                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).hideProgress();
                }
            }
        });
    }

    private void initOkHttp() {
        // 통신을 할 땐, manifest에 인터넷 허용 권한을 넣어줘야함
        // OkHttp로 통신 시작
        HttpConnection.getInstance().requestWebServer(new Callback() {

            // 응답 실패했을 때 실행되는 콜백 메소드
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("HttpConnection", "onFailure");
            }

            // 응답 성공했을 때 실행되는 콜백 메소드
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("HttpConnection", "onResponse");

                // 응답받는 response 데이터의 body 를 로그출력
                Log.d("HttpConnection", "response : " + response.body().string());
            }
        });
        // OkHttp로 통신 끝
    }

    public interface OnChangeWeather {
        void change(WeatherData weatherData);
    }
}
