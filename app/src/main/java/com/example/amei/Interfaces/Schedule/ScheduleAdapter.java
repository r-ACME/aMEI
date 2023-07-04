package com.example.amei.Interfaces.Schedule;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.amei.Modelos.Schedule;

import java.util.List;

public class ScheduleAdapter extends BaseAdapter {

    private List<Schedule> schedules;
    private Context context;

    public ScheduleAdapter(Context context, List<Schedule> schedules) {
        this.schedules = schedules;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return this.schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule schedule = this.schedules.get(position);
        TextView txtTitulo;

        if (convertView == null) {
            txtTitulo = new TextView(parent.getContext());
        } else {
            txtTitulo = (TextView) convertView;
        }

        String date = schedule.getDatetime();


        if (date.length() > 0) {
            String finalselectedDate = date.substring(11, 16);
            txtTitulo.setText(finalselectedDate + " - " + schedule.getTitle());
        } else {
            txtTitulo.setText(schedule.getTitle());
        }

        Configuration configuration = context.getResources().getConfiguration();
        int orientation = configuration.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            txtTitulo.setTextSize(25);
        }
        else {
            txtTitulo.setTextSize(15);
        }
        return txtTitulo;
    }

}
