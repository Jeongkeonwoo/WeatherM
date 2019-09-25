package com.example.weatherm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherm.api_okhttp.HttpConnection;
import com.example.weatherm.api_retrofit.ApiManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initOkHttp();
        initRetrofit();

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

    private void initRetrofit() {
        apiManager = ApiManager.getInstance();

        // OkHttp의 Call 이랑 클래스명이 겹쳐서 retrofit2.Call 을 써준것
        retrofit2.Call<String> response = apiManager.getWeather("Seoul");

        response.enqueue(new retrofit2.Callback<String>() {
            // 응답 성공
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                Log.d("Retrofit", "onResponse");
            }

            // 응답 실패
            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Log.d("Retrofit", "onFailure");


            }
        });
    }
}
