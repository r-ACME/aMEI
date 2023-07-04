package com.example.amei.Interfaces.Schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.Negocios.Banco.SchedulesDAO;
import com.example.amei.Negocios.DateUtils;
import com.example.amei.Modelos.Schedule;
import com.example.amei.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleCreate extends AppCompatActivity {

    private Button btnDateTimePicker;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Date scheduleDateTime;
    private CalendarView scheduleDateTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);

        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;

        EditText txtTitle = findViewById(R.id.txt_schedule_create_title);
        EditText txtDescription = findViewById(R.id.txt_schedule_create_description);
        btnDateTimePicker = findViewById(R.id.btn_schedule_create_date_time);

        Bundle receiveSchedule = getIntent().getExtras();
        Schedule currentSchedule;

        if (receiveSchedule != null) {
            currentSchedule = (Schedule) receiveSchedule.getSerializable("scheduleToEdit");
        } else {
            currentSchedule = new Schedule(0, "", "", "", "", 0);
        }

        txtTitle.setText(currentSchedule.getTitle());
        txtDescription.setText(currentSchedule.getDescription());

        if (!currentSchedule.getDatetime().isEmpty()) {
            scheduleDateTime = DateUtils.parseStringToDate(currentSchedule.getDatetime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            btnDateTimePicker.setText(dateFormat.format(scheduleDateTime));
        }

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            scheduleDateTimeView = findViewById(R.id.schedule_create_calendar);
            scheduleDateTimeView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    showTimePicker(year, month, dayOfMonth);
                }
            });
        } else {
            btnDateTimePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateTimePicker();
                }
            });
        }

        Button btnSaveSchedule = findViewById(R.id.btn_schedule_create_save);

        btnSaveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchedulesDAO schedulesDAO = new SchedulesDAO(ScheduleCreate.this);
                List<Schedule> schedules = new ArrayList<>();
                DataBase db = new DataBase(ScheduleCreate.this);

                if (currentSchedule.getId() != 0) {
                    if (scheduleDateTime != null) {
                        schedules.add(new Schedule(currentSchedule.getId(), DateUtils.formatDateToString(scheduleDateTime),
                                txtTitle.getText().toString(), txtDescription.getText().toString(), "", 0));
                        db.runNonSelectQuery(schedulesDAO.updateQuerys(schedules));
                    }
                } else {
                    if (scheduleDateTime != null) {
                        schedules.add(new Schedule(0, DateUtils.formatDateToString(scheduleDateTime),
                                txtTitle.getText().toString(), txtDescription.getText().toString(), "", 0));
                        db.runNonSelectQuery(schedulesDAO.insertQuerys(schedules));
                    }
                }

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(ScheduleCreate.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        showTimePicker(year, month, dayOfMonth);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void showTimePicker(final int year, final int month, final int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(ScheduleCreate.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(year, month, dayOfMonth, hourOfDay, minute);
                        scheduleDateTime = calendar.getTime();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                        String selectedDateTime = dateFormat.format(scheduleDateTime);
                        btnDateTimePicker.setText(selectedDateTime);
                    }
                }, hourOfDay, minute, true);

        timePickerDialog.show();
    }

}
