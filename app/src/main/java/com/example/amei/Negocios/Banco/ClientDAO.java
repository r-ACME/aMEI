package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO{

    DataBase banco;

    public ClientDAO(Context context) {
        this.banco = new DataBase(context);
    }

    public ClientDAO(DataBase dataBase) {
        this.banco = dataBase;
    }

    @SuppressLint("Range")
    public List<Client> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<Client> clients = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM users", null);

            if(line.moveToFirst()){
                do{
                    Client client = new Client();
                    client.setId(line.getInt(line.getColumnIndex("id")));
                    client.setPerson_id(line.getInt(line.getColumnIndex("person_id")));
                    client.setCompany_id(line.getInt(line.getColumnIndex("company_id")));
                    client.setType(line.getString(line.getColumnIndex("type")));
                    client.setActive(line.getInt(line.getColumnIndex("active")));

                    clients.add(client);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return clients;
    }
    public List<String> insertQuerys(List<Client> clients){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Client client : clients){
                Integer currentId = banco.fetchLastId("client") + 1;
                querys.add("INSERT INTO client VALUES(" + currentId + ", " + client.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }
}
