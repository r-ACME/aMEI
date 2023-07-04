package com.example.amei.Interfaces.Schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.amei.Negocios.API.LocalDataManager;
import com.example.amei.Negocios.DateUtils;
import com.example.amei.R;

import java.util.Calendar;
import java.util.Date;

public class ScheduleView extends Fragment {


    private static final int REQUEST_CREATE_SCHEDULE = 1;

    public ScheduleView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar o layout do fragmento

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        FragmentContainerView scheduleList = rootView.findViewById(R.id.frag_schedule_list);

        CalendarView calendarView = rootView.findViewById(R.id.schedule_calendar);
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        Button btnCreateSchedule = rootView.findViewById(R.id.btn_schedule_create);



        calendarView.setDate(calendar.getTimeInMillis(), true, true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                Date selectedDate = selectedCalendar.getTime();

                LocalDataManager storageSelectedDate = new LocalDataManager(getActivity(), "current_date");
                storageSelectedDate.saveString(DateUtils.formatDateToString(selectedDate), "selectedDate");

                // Recarregar o fragmento ScheduleList com a nova data selecionada
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ScheduleList scheduleListFragment = new ScheduleList();
                fragmentTransaction.replace(R.id.frag_schedule_list, scheduleListFragment);
                fragmentTransaction.commit();

            }
        });



        btnCreateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ScheduleView.super.getActivity(), ScheduleCreate.class);
                startActivityForResult(intent, REQUEST_CREATE_SCHEDULE);

            }
        });
        return rootView;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CREATE_SCHEDULE && resultCode == Activity.RESULT_OK) {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ScheduleList scheduleListFragment = new ScheduleList();
            fragmentTransaction.replace(R.id.frag_schedule_list, scheduleListFragment);
            fragmentTransaction.commit();
        }
    }


}
