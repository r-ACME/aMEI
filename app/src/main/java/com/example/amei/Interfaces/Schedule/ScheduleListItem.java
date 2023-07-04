package com.example.amei.Interfaces.Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amei.Modelos.Schedule;
import com.example.amei.R;

public class ScheduleListItem extends Fragment {

    Schedule schedule;
    public ScheduleListItem(){
        super(R.layout.fragment_schedule_list_item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_list_item, container, false);
    }

    public void updateSchedule(Schedule schedule){

        TextView txtTitle = this.getView().findViewById(R.id.txt_schedule_title);
        txtTitle.setText(schedule.getTitle());

        TextView txtDescription = this.getView().findViewById(R.id.txt_schedule_description);
        txtDescription.setText(schedule.getDescription());

        TextView txtDate = this.getView().findViewById(R.id.txt_schedule_date);
        txtDate.setText(schedule.getDatetime());

    }




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Locations.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleListItem newInstance(String param1, String param2) {
        ScheduleListItem fragment = new ScheduleListItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


}