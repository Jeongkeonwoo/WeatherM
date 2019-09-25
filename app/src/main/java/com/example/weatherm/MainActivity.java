package com.example.weatherm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherm.api_okhttp.HttpConnection;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 통신을 할 땐, manifest에 인터넷 허용 권한을 넣어줘야함
        // OkHttp로 통신 시작
        HttpConnection.getInstance().requestWebServer(new Callback() {

            // 응답 실패했을 때 실행되는 콜백 메소드
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("HttpConnection", "onFailure");
            }

            // 응답 성곡했을 때 실행되는 콜백 메소드
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("HttpConnection", "onResponse");

                // 응답받는 response 데이터의 body 를 로그출력
                Log.d("HttpConnection", "response : " + response.body().string());
            }
        });
        // OkHttp로 통신 끝
    }
}
