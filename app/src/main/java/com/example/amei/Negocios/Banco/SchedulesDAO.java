package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Schedule;

import java.util.ArrayList;
import java.util.List;

public class SchedulesDAO {

    public DataBase banco;

    public SchedulesDAO(Context context) {
        this.banco = new DataBase(context);
    }

    @SuppressLint("Range")
    public List<Schedule> getAll() {
        SQLiteDatabase database = banco.getReadableDatabase();
        List<Schedule> schedules = new ArrayList<>();

        try {
            Cursor cursor = database.rawQuery("SELECT * FROM schedule ORDER BY datetime", null);

            if (cursor.moveToFirst()) {
                do {
                    Schedule schedule = new Schedule();
                    schedule.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    schedule.setDatetime(cursor.getString(cursor.getColumnIndex("datetime")));
                    schedule.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    schedule.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    schedule.setAlerts(cursor.getString(cursor.getColumnIndex("alerts")));
                    schedule.setClient_id(cursor.getInt(cursor.getColumnIndex("client_id")));

                    schedules.add(schedule);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d("ERROR " + e.getClass(), e.getMessage());
        } finally {
            database.close();
        }

        return schedules;
    }

    public List<String> insertQuerys(List<Schedule> schedules) {
        List<String> queries = new ArrayList<>();
        SQLiteDatabase database = banco.getWritableDatabase();

        try {
            for (Schedule schedule : schedules) {
                int currentId = banco.fetchLastId("schedule") + 1;
                queries.add("INSERT INTO schedule VALUES(" + currentId + ", " + schedule.queryForInsert() + ")");
            }
        } catch (Exception e) {
            Log.d("ERROR " + e.getClass(), e.getMessage());
        } finally {
            database.close();
        }

        return queries;
    }

    public List<String> updateQuerys(List<Schedule> schedules) {
        List<String> queries = new ArrayList<>();
        for (Schedule schedule : schedules) {
            queries.add(schedule.queryForUpdate());
        }
        return queries;
    }
}
