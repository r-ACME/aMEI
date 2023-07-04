package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO{

    public DataBase banco;

    public EnderecoDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    @SuppressLint("Range")
    public List<Endereco> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<Endereco> adress = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM adress", null);
            if(line.moveToFirst()){
                do{
                    Endereco currentAdress = new Endereco();
                    currentAdress.setId(line.getInt(line.getColumnIndex("id")));
                    currentAdress.setLogradouro(line.getString(line.getColumnIndex("logradouro")));
                    currentAdress.setNumero(line.getString(line.getColumnIndex("numero")));
                    currentAdress.setComplemento(line.getString(line.getColumnIndex("complemento")));
                    currentAdress.setCep(line.getString(line.getColumnIndex("cep")));
                    currentAdress.setBairro(line.getString(line.getColumnIndex("bairro")));
                    currentAdress.setMunicipio(line.getString(line.getColumnIndex("municipio")));
                    currentAdress.setUf(line.getString(line.getColumnIndex("uf")));

                    adress.add(currentAdress);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return adress;
    }

    public List<String> insertQuerys(List<Endereco> adress){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Endereco currentAdress : adress){
                Integer currentId = banco.fetchLastId("adress") + 1;
                querys.add("INSERT INTO adress VALUES(" + currentId + ", " + currentAdress.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }
}
