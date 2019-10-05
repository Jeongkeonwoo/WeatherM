package com.example.weatherm.fragment.daytime;

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

import java.util.ArrayList;

public class DaytimeFragment extends Fragment {

    private RecyclerView recyclerView;
    private DaytimeAdapter daytimeAdapter;
    private final int day = 8;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daytime, null);
        //TextView textView = view.findViewById(R.id.text);
        recyclerView = view.findViewById(R.id.daytime_recyclerview);

        ArrayList<Data> data = new ArrayList<>();

        for (int i = 0; i < day ; i++){
            //data.add(new Data(R.mipmap.ic_launcher, ""));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        daytimeAdapter = new DaytimeAdapter();

        daytimeAdapter.setDataList(data);

        recyclerView.setAdapter(daytimeAdapter);

        return view;
    }
}
