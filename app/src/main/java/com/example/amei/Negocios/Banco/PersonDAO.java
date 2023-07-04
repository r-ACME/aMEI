package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO{

    DataBase banco;

    public PersonDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    @SuppressLint("Range")
    public List<Person> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<Person> persons = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM person", null);

            if(line.moveToFirst()){
                do{
                    Person person = new Person();
                    person.setId(line.getInt(line.getColumnIndex("id")));
                    person.setAdress_id(line.getInt(line.getColumnIndex("adress_id")));
                    person.setName(line.getString(line.getColumnIndex("name")));
                    person.setDocument(line.getString(line.getColumnIndex("document")));
                    person.setPhone(line.getString(line.getColumnIndex("phone")));
                    person.setEmail(line.getString(line.getColumnIndex("email")));

                    persons.add(person);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return persons;
    }

    public List<String> insertQuerys(List<Person> persons){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Person person : persons){
                Integer currentId = banco.fetchLastId("person") + 1;
                querys.add("INSERT INTO person VALUES(" + currentId + ", " + person.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }


}
