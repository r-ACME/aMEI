package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Schedule;
import com.example.amei.Modelos.ScheduleService;

import java.util.ArrayList;
import java.util.List;

public class ScheduleServiceDAO {

    public DataBase banco;

    public ScheduleServiceDAO(Context context) {
        this.banco = new DataBase(context);
    }

    @SuppressLint("Range")
    public List<ScheduleService> getAll() {
        SQLiteDatabase database = banco.getReadableDatabase();
        List<ScheduleService> scheduleServices = new ArrayList<>();

        try {
            Cursor cursor = database.rawQuery("SELECT * FROM schedule_service", null);

            if (cursor.moveToFirst()) {
                do {
                    ScheduleService scheduleService = new ScheduleService();
                    scheduleService.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    scheduleService.setSchedule_id(cursor.getInt(cursor.getColumnIndex("schedule_id")));
                    scheduleService.setService_id(cursor.getInt(cursor.getColumnIndex("service_id")));
                    scheduleService.setAmount_service(cursor.getString(cursor.getColumnIndex("amount_service")));
                    scheduleService.setProduct_id(cursor.getInt(cursor.getColumnIndex("product_id")));
                    scheduleService.setAmount_product(cursor.getString(cursor.getColumnIndex("amount_product")));
                    scheduleService.setCompletion(cursor.getInt(cursor.getColumnIndex("completion")));
                    scheduleService.setDiscount(cursor.getString(cursor.getColumnIndex("discount")));

                    scheduleServices.add(scheduleService);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERROR " + e.getClass(), e.getMessage());
        } finally {
            database.close();
        }

        return scheduleServices;
    }

    public List<String> insertQuerys(List<ScheduleService> scheduleServices) {
        List<String> queries = new ArrayList<>();
        SQLiteDatabase database = banco.getWritableDatabase();

        try {
            for (ScheduleService scheduleService : scheduleServices) {
                int currentId = banco.fetchLastId("schedule_service") + 1;
                queries.add("INSERT INTO schedule_service VALUES(" + currentId + ", " + scheduleService.queryForInsert() + ")");
            }
        } catch (Exception e) {
            Log.d("ERROR " + e.getClass(), e.getMessage());
        } finally {
            database.close();
        }

        return queries;
    }

    public List<String> updateQuerys(List<ScheduleService> scheduleServices) {
        List<String> queries = new ArrayList<>();
        for (ScheduleService scheduleService : scheduleServices) {
            queries.add(scheduleService.queryForUpdate());
        }
        return queries;
    }
}
