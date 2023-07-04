package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Atividade;

import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO{

    public DataBase banco;

    public AtividadeDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    public AtividadeDAO(DataBase dataBase) {
        this.banco = dataBase;
    }

    @SuppressLint("Range")
    public List<Atividade> getAll(Integer companyId){
        SQLiteDatabase dataBase = banco.getReadableDatabase();

        List<Atividade> activities = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT activity.* FROM qsa JOIN activity.id == company_activities.activity_id WHERE company_activities.company_id = " + companyId, null);

            if(line.moveToFirst()){
                do{
                    Atividade activity = new Atividade();
                    activity.setId(line.getInt(line.getColumnIndex("id")));
                    activity.setCode(line.getString(line.getColumnIndex("code")));
                    activity.setText(line.getString(line.getColumnIndex("text")));

                    activities.add(activity);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return activities;
    }

    public List<String> insertQuerys(Integer companyID, List<Atividade> activities){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Atividade activity : activities){
                Integer currentId = banco.fetchLastId("activity") + 1;
                querys.add("INSERT INTO activity VALUES(" + currentId + ", " + activity.queryForInsert() + ")");
                querys.add("INSERT INTO company_activities VALUES(" + companyID + currentId +")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }
}
