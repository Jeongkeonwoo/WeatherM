package com.example.weatherm.fragment.daytime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherm.Data;
import com.example.weatherm.R;

import java.util.ArrayList;

public class DaytimeAdapter extends RecyclerView.Adapter<DaytimeAdapter.DaytimeHolder> {

    private ArrayList<Data> dataList;

    public void setDataList(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DaytimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //view 실체화
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_item, parent, false);
        //view 를 holder 에 넣어줌
        DaytimeHolder holder = new DaytimeHolder(view);

        return holder;
    }

    //onCreateViewHolder() 에서 holder 를 return 받은 holder 에 데이터를 셋팅해준다.
    @Override
    public void onBindViewHolder(@NonNull DaytimeHolder holder, int position) {
        Data data = dataList.get(position);

//        holder.daytime_weather_weekly.setText(data.getText());
//        holder.daytime_weather_date.setText(data.getText());
//        holder.daytime_weather_icon.setImageResource(data.getImg());
//        holder.daytime_weather_hightemperature.setText(data.getText());
//        holder.daytime_weather_lowtemperature.setText(data.getText());


        holder.daytime_weather_weekly.setText(data.getText() + "토 요일");
        holder.daytime_weather_date.setText(data.getText() + "날짜");
        holder.daytime_weather_icon.setImageResource(data.getImg());
        holder.daytime_weather_hightemperature.setText(data.getText() + "19도");
        holder.daytime_weather_lowtemperature.setText(data.getText() + "17도");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DaytimeHolder extends RecyclerView.ViewHolder {

        private TextView daytime_weather_weekly;
        private TextView daytime_weather_date;
        private ImageView daytime_weather_icon;
        private TextView daytime_weather_hightemperature;
        private TextView daytime_weather_lowtemperature;

        public DaytimeHolder(@NonNull View itemView) {
            super(itemView);

            daytime_weather_weekly = itemView.findViewById(R.id.daytime_weather_weekly);
            daytime_weather_date = itemView.findViewById(R.id.daytime_weather_date);
            daytime_weather_icon = itemView.findViewById(R.id.daytime_weather_icon);
            daytime_weather_hightemperature = itemView.findViewById(R.id.daytime_weather_hightemperature);
            daytime_weather_lowtemperature = itemView.findViewById(R.id.daytime_weather_lowtemperature);
        }
    }
}
