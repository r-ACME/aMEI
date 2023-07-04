package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.QSA;

import java.util.ArrayList;
import java.util.List;

public class QSADAO{

    DataBase banco;

    QSADAO(Context context){
        this.banco = new DataBase(context);
    }

    @SuppressLint("Range")
    public List<QSA> getAll(Integer companyId){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<QSA> qsas = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT qsa.* FROM qsa JOIN qsa.id == company_qsa.qsa_id WHERE company_qsa.company_id = " + companyId, null);

            if(line.moveToFirst()){
                do{
                    QSA qsa = new QSA();
                    qsa.setNome(line.getString(line.getColumnIndex("nome")));

                    qsas.add(qsa);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return qsas;
    }

    public List<String> insertQuerys(Integer companyID, List<QSA> qsas){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (QSA qsa : qsas){
                Integer currentId = banco.fetchLastId("qsa") + 1;
                querys.add("INSERT INTO qsa VALUES(" + currentId + ", " + qsa.queryForInsert() + ")");
                querys.add("INSERT INTO company_qsa VALUES(" + companyID + currentId +")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }
}
