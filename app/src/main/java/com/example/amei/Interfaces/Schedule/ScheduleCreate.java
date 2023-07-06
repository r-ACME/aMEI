package com.example.amei.Interfaces.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.amei.Modelos.Client;
import com.example.amei.Modelos.Product;
import com.example.amei.Modelos.ScheduleService;
import com.example.amei.Modelos.Service;
import com.example.amei.Negocios.Banco.ClientDAO;
import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.Negocios.Banco.ProductDAO;
import com.example.amei.Negocios.Banco.ScheduleServiceDAO;
import com.example.amei.Negocios.Banco.SchedulesDAO;
import com.example.amei.Negocios.Banco.ServiceDAO;
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

    private List<Client> allClients;
    private List<Product> allProducts;
    private List<Service> allServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);

        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;
        ClientDAO clientDAO = new ClientDAO(this);
        ProductDAO productDAO = new ProductDAO(this);
        ServiceDAO serviceDAO = new ServiceDAO(this);
        ScheduleServiceDAO scheduleServiceDAO = new ScheduleServiceDAO(this);

        TextView lblOutOfClients = findViewById(R.id.lbl_schedule_create_out_of_clients);
        TextView lblOutOfProducts = findViewById(R.id.lbl_schedule_create_out_of_products);
        TextView lblOutOfServices = findViewById(R.id.lbl_schedule_create_out_of_services);
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

        allClients = clientDAO.getAll();
        Spinner spClient = findViewById(R.id.sp_schedule_create_client_selection);
        ArrayAdapter<Client> adapterClient = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                allClients
        );
        adapterClient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClient.setAdapter(adapterClient);

        allProducts = productDAO.getAll();
        Spinner spProduct = findViewById(R.id.sp_schedule_create_products_selection);
        ArrayAdapter<Product> adapterProduct = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                allProducts
        );
        adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(adapterProduct);

        allServices = serviceDAO.getAll();
        Spinner spService = findViewById(R.id.sp_schedule_create_services_selection);
        ArrayAdapter<Service> adapterService = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                allServices
        );
        adapterService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spService.setAdapter(adapterService);

        txtTitle.setText(currentSchedule.getTitle());
        txtDescription.setText(currentSchedule.getDescription());

        if (!currentSchedule.getDatetime().isEmpty()) {
            scheduleDateTime = DateUtils.parseStringToDate(currentSchedule.getDatetime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            btnDateTimePicker.setText(dateFormat.format(scheduleDateTime));
        }

        LinearLayoutCompat.LayoutParams gone = new LinearLayoutCompat.LayoutParams(0,0);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            LinearLayoutCompat.LayoutParams show_client = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            show_client.weight = 1;

            if(allClients.size() == 0){
                spClient.setLayoutParams(gone);
                spClient.setVisibility(View.GONE);
                lblOutOfClients.setLayoutParams(show_client);
            }

            if(allProducts.size() == 0){
                spProduct.setLayoutParams(gone);
                spProduct.setVisibility(View.GONE);
                lblOutOfProducts.setLayoutParams(show_client);
            }

            if(allServices.size() == 0){
                spService.setLayoutParams(gone);
                spService.setVisibility(View.GONE);
                lblOutOfServices.setLayoutParams(show_client);
            }

            scheduleDateTimeView = findViewById(R.id.schedule_create_calendar);
            scheduleDateTimeView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    showTimePicker(year, month, dayOfMonth);
                }
            });
        } else {

            LinearLayoutCompat.LayoutParams show_client = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            show_client.weight = 1;
            if(allClients.size() == 0){

                spClient.setLayoutParams(gone);
                lblOutOfClients.setLayoutParams(show_client);
            }

            if(allProducts.size() == 0){
                spProduct.setLayoutParams(gone);
                lblOutOfProducts.setLayoutParams(show_client);
            }

            if(allServices.size() == 0){
                spService.setLayoutParams(gone);
                lblOutOfServices.setLayoutParams(show_client);
            }
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
                List<String> querys = new ArrayList<>();
                List<Schedule> schedules = new ArrayList<>();
                DataBase db = new DataBase(ScheduleCreate.this);
                Client client = (Client) spClient.getSelectedItem();

                if (currentSchedule.getId() != 0) {
                    if (scheduleDateTime != null) {
                        schedules.add(new Schedule(currentSchedule.getId(), DateUtils.formatDateToString(scheduleDateTime),
                                txtTitle.getText().toString(), txtDescription.getText().toString(), "", client.getId()));
                        for (String schedule : schedulesDAO.updateQuerys(schedules)) {
                            querys.add(schedule);
                        }

                    }
                } else {
                    if (scheduleDateTime != null) {
                        //ScheduleService thisSchedule = new ScheduleService(0, currentSchedule.getId(), );
                        schedules.add(new Schedule(0, DateUtils.formatDateToString(scheduleDateTime),
                                txtTitle.getText().toString(), txtDescription.getText().toString(), "", client.getId()));
                        for (String schedule : schedulesDAO.updateQuerys(schedules)) {
                            querys.add(schedule);
                        }

                    }
                }

                db.runNonSelectQuery(querys);
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
