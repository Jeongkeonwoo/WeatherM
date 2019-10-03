package com.example.weatherm.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.weatherm.fragment.daytime.DaytimeFragment;
import com.example.weatherm.fragment.preset.PresentFragment;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData;

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        mData = new ArrayList<>();
        mData.add(new PresentFragment());
        mData.add(new DaytimeFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return mData.get(position);
    }

    @Override
    public int getCount() {

        return mData.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "현재 날씨";
            case 1:
                return "주간 날씨";
            default:
                return null;
        }
    }
}
