package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {

    DataBase banco;

    public ServiceDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    @SuppressLint("Range")
    public List<Service> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<Service> services = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM service", null);

            if(line.moveToFirst()){
                do{
                    Service service = new Service();
                    service.setId(line.getInt(line.getColumnIndex("id")));
                    service.setName(line.getString(line.getColumnIndex("name")));
                    service.setDescription(line.getString(line.getColumnIndex("description")));
                    service.setValue(line.getString(line.getColumnIndex("value")));
                    service.setDuration(line.getString(line.getColumnIndex("duration")));
                    service.setType_id(line.getInt(line.getColumnIndex("type_id")));

                    services.add(service);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return services;
    }

    public List<String> insertQuerys(List<Service> services){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Service service : services){
                Integer currentId = banco.fetchLastId("service") + 1;
                querys.add("INSERT INTO service VALUES(" + currentId + ", " + service.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }


}
