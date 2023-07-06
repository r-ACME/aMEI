package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Person;
import com.example.amei.Modelos.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    DataBase banco;

    public ProductDAO(Context context) {
        this.banco = new DataBase(context);;
    }

    @SuppressLint("Range")
    public List<Product> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<Product> products = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM product", null);

            if(line.moveToFirst()){
                do{
                    Product product = new Product();
                    product.setId(line.getInt(line.getColumnIndex("id")));
                    product.setName(line.getString(line.getColumnIndex("name")));
                    product.setDescription(line.getString(line.getColumnIndex("description")));
                    product.setValue(line.getString(line.getColumnIndex("value")));
                    product.setStorage(line.getString(line.getColumnIndex("storage")));
                    product.setType_id(line.getInt(line.getColumnIndex("type_id")));

                    products.add(product);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return products;
    }

    public List<String> insertQuerys(List<Product> products){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Product product : products){
                Integer currentId = banco.fetchLastId("product") + 1;
                querys.add("INSERT INTO product VALUES(" + currentId + ", " + product.queryForInsert() + ")");
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }


}
