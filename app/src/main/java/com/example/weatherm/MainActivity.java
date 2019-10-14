package com.example.weatherm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.example.weatherm.fragment.MyPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    // 사용자의 입력을 제한
    // 화면전환, api 요청시에 사용
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager viewPager = findViewById(R.id.pager);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
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
