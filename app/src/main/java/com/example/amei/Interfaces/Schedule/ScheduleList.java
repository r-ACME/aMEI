package com.example.amei.Interfaces.Schedule;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amei.Negocios.API.LocalDataManager;
import com.example.amei.Negocios.Banco.SchedulesDAO;
import com.example.amei.Modelos.Schedule;
import com.example.amei.R;
import com.example.amei.Negocios.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScheduleList  extends ListFragment {

    List<Schedule> allSchedules = new ArrayList<>();
    List<Schedule> schedulesOfTheDay = new ArrayList<>();
    private static final int REQUEST_CREATE_SCHEDULE = 1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);
        List<Schedule> nonSchedule = new ArrayList<>();
        nonSchedule.add(new Schedule(0,"","Você não possui agendamentos para hoje", "", "", 0));
        //nonSchedule.add(new Schedule(0,"","Selecione um agendamento para ver os detalhes", "", "", 0));

        LocalDataManager local = new LocalDataManager(getActivity(), "current_date");
        String selectedDate = local.getString("selectedDate");
        SchedulesDAO schedulesDAO = new SchedulesDAO(super.getContext());

        ScheduleAdapter adapter;

        if(selectedDate != null) {
            String finalselectedDate = selectedDate.substring(0, 10);
            selectedDate = finalselectedDate;

            allSchedules = schedulesDAO.getAll();
            Log.d("ALL_SCHEDULES", allSchedules.size() + "");

            for (Schedule schedule : allSchedules) {
                if (schedule.getDatetime().contains(selectedDate)) {
                    schedulesOfTheDay.add(schedule);
                }
            }
            if (schedulesOfTheDay.size() == 0) {
                adapter = new ScheduleAdapter(this.getActivity(), nonSchedule);
            } else {
                Log.d("SCHEDULES_IN_DAY", schedulesOfTheDay.size() + "");
                adapter = new ScheduleAdapter(this.getActivity(), schedulesOfTheDay);
            }

            setListAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(this.schedulesOfTheDay.size() == 0){
            ToastUtil.showTopToast(getActivity(), "Não há agendamentos hoje!");
//            Schedule nonSchedule = new Schedule(0,"","Você não possui agendamentos para hoje", "", "", 0);
//
//            FragmentManager fm = this.getParentFragmentManager();
//            ScheduleListItem expand = (ScheduleListItem) fm.findFragmentById(R.id.frag_schedule_create);
//
//            expand.updateSchedule(nonSchedule);
        }
        else {
            Schedule selectedSchedule = this.schedulesOfTheDay.get(position);
            Configuration configuration = getResources().getConfiguration();
            int orientation = configuration.orientation;

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Bundle sendData = new Bundle();
                sendData.putSerializable("scheduleToEdit", (Serializable) selectedSchedule);
                Intent intent = new Intent( getActivity(), ScheduleCreate.class);
                intent.putExtras(sendData);
                startActivityForResult(intent, REQUEST_CREATE_SCHEDULE);

            } else{
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ScheduleListItem scheduleListItemFragment = (ScheduleListItem) fragmentManager.findFragmentById(R.id.frag_details);
                scheduleListItemFragment.updateSchedule(selectedSchedule);

                fragmentTransaction.commit();

            }



        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CREATE_SCHEDULE && resultCode == Activity.RESULT_OK) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ScheduleList scheduleListFragment = new ScheduleList();
            fragmentTransaction.replace(R.id.frag_schedule_list, scheduleListFragment);
            fragmentTransaction.commit();
        }
    }

}