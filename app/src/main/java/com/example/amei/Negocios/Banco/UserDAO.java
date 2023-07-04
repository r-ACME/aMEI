package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO{

    public DataBase banco;

    public UserDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    @SuppressLint("Range")
    public List<User> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<User> users = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM users", null);

            if(line.moveToFirst()){
                do{
                    User user = new User();
                    user.setId(line.getInt(line.getColumnIndex("id")));
                    user.setCompanyId(line.getInt(line.getColumnIndex("company_id")));
                    user.setCnpj(line.getString(line.getColumnIndex("cnpj")));
                    user.setPassword(line.getString(line.getColumnIndex("password")));

                    users.add(user);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return users;
    }

    public List<String> insertQuerys(List<User> users){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (User user : users){
                Integer currentId = banco.fetchLastId("users") + 1;
                querys.add("INSERT INTO users VALUES(" + currentId + ", " + user.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }

    public Boolean userExists(User currentUser){

        for (User user : this.getAll()) {
            if (user.getCnpj().equals(currentUser.getCnpj())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    static public Boolean validateUser(User currentUser, List<User> users){

        for (User user : users){
            if (user.getCnpj().equals(currentUser.getCnpj())){
                if (user.getPassword().equals(currentUser.getPassword())){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    static public User getValidUser(User currentUser, List<User> users){
        User error = new User();
        for (User user : users){
            if (user.getCnpj().equals(currentUser.getCnpj())){
                if (user.getPassword().equals(currentUser.getPassword())){
                    return user;
                }
                return error;
            }
        }
        return error;
    }
}
