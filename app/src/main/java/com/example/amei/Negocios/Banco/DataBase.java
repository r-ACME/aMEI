package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.amei.Negocios.StringExtensions;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATA_BASE = "aMEI.db";

    private static final int DATA_BASE_VERSION = 1;

    public SQLiteDatabase dataBase;

    public DataBase(Context context){
        super(context, DATA_BASE, null, DATA_BASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        List<String> creates = new ArrayList<>();

        creates.add("CREATE TABLE faq(id int not null,question varchar(5000),answer varchar(100000),PRIMARY KEY (id));");
        creates.add("CREATE TABLE type(id int not null, name varchar(1000), expenses int, revenue int, PRIMARY KEY (id));");
        creates.add("CREATE TABLE financial_statement(id int not null, type_id int not null, description varchar(1000), value varchar(1000), recurrent int, flow varchar(1000), PRIMARY KEY (id), FOREIGN KEY (type_id) REFERENCES type(id));");
        creates.add("CREATE TABLE service(id int not null, name varchar(1000), description varchar(1000), value varchar(1000), duration varchar(1000), type_id int not null, PRIMARY KEY (id), FOREIGN KEY (type_id) REFERENCES type(id));");
        creates.add("CREATE TABLE product(id int not null, name varchar(1000), description varchar(1000), value varchar(1000), storage varchar(1000), type_id int not null, PRIMARY KEY (id), FOREIGN KEY (type_id) REFERENCES type(id));");
        creates.add("CREATE TABLE inventory(id int not null, date varchar(1000), amount varchar(1000), product_id int not null, PRIMARY KEY (id), FOREIGN KEY (product_id) REFERENCES product(id));");
        creates.add("CREATE TABLE qsa(id int not null, nome varchar(1000), qual varchar(1000), pais_origem varchar(1000), nome_rep_legal varchar(1000), qual_rep_legal varchar(1000), PRIMARY KEY (id));");
        creates.add("CREATE TABLE activity(id int not null, code varchar(1000), text varchar(1000), type varchar(1000) DEFAULT ‘primary’, PRIMARY KEY (id) );");
        creates.add("CREATE TABLE adress(id int not null, logradouro varchar(1000), numero varchar(1000), complemento varchar(1000), cep varchar(1000), bairro varchar(1000), municipio varchar(1000), uf varchar(1000), PRIMARY KEY (id));");
        creates.add("CREATE TABLE person(id int not null, adress_id int, name varchar(1000), document varchar(1000), phone varchar(1000), email varchar(1000), PRIMARY KEY (id), FOREIGN KEY (adress_id) REFERENCES adress(id));");
        creates.add("CREATE TABLE rh(id int not null, person_id int not null, role varchar(1000), wage varchar(1000), extra varchar(1000), PRIMARY KEY (id), FOREIGN KEY (person_id) REFERENCES person(id));");
        creates.add("CREATE TABLE clock_in_out(id int not null, rh_id int not null, date varchar(1000), day_in varchar(1000), day_out varchar(1000), PRIMARY KEY (id), FOREIGN KEY (rh_id) REFERENCES rh(id));");
        creates.add("CREATE TABLE company(id int NOT NULL, status varchar(1000), ultima_atualizacao varchar(1000), cnpj varchar(20), tipo varchar(1000), porte varchar(1000), nome varchar(1000), fantasia varchar(1000), abertura varchar(1000), natureza_juridica varchar(1000), adress_id int, email varchar(1000), telefone varchar(1000), efr varchar(1000), situacao varchar(1000), data_situacao varchar(1000), motivo_situacao varchar(1000), situacao_especial varchar(1000), data_situacao_especial varchar(1000), capital_social varchar(1000), PRIMARY KEY (id), FOREIGN KEY (adress_id) REFERENCES adress(id));");
        creates.add("CREATE TABLE client(id int not null, person_id int, company_id int, type varchar(1000), active int, PRIMARY KEY (id), FOREIGN KEY (person_id) REFERENCES person(id), FOREIGN KEY (company_id) REFERENCES company(id));");
        creates.add("CREATE TABLE schedule(id int not null, datetime varchar(1000), title varchar(1000), description varchar(1000), alerts varchar(1000), client_id int, PRIMARY KEY (id), FOREIGN KEY (client_id) REFERENCES client(id));");
        creates.add("CREATE TABLE schedule_service(id int not null, schedule_id int, service_id int, amount_service varchar(1000), product_id int, amount_product varchar(1000), completion int, discount varchar(1000), PRIMARY KEY (id), FOREIGN KEY (schedule_id) REFERENCES schedule(id), FOREIGN KEY (service_id) REFERENCES service(id), FOREIGN KEY (product_id) REFERENCES product(id));");
        creates.add("CREATE TABLE users(id int not null, company_id int not null, cnpj varchar(20), password varchar(1000), PRIMARY KEY (id), FOREIGN KEY (company_id) REFERENCES company(id));");
        creates.add("CREATE TABLE accounting(id int not null, balance varchar(1000), date varchar(1000), user_id int not null, PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES user(id));");
        creates.add("CREATE TABLE company_activities(company_id int not null, activity_id int not null, FOREIGN KEY (company_id) REFERENCES company(id), FOREIGN KEY (activity_id) REFERENCES activity(id));");
        creates.add("CREATE TABLE company_qsa(company_id int not null, qsa_id int not null, FOREIGN KEY (company_id) REFERENCES company(id), FOREIGN KEY (qsa_id) REFERENCES qsa(id));");
        creates.add("INSERT INTO company values (0, 'status', 'ultima_atualizacao', 'cnpj', 'tipo', 'porte', 'nome', 'fantasia', 'abertura', 'natureza_juridica', 0, 'email', 'telefone', 'efr', 'situacao', 'data_situacao', 'motivo_situacao', 'situacao_especial', 'data_situacao_especial', 'capital_social')");
        creates.add("INSERT INTO person VALUES (0, 0, 'Not a person', '00000000000', '00000000000', 'not@person.com' )");
        creates.add("INSERT INTO adress VALUES (0, 'Rua', 'Numero', 'Complemento', 'CEP', 'Bairro', 'Cidade', 'Estado')");

        for (String create: creates) {
            sqLiteDatabase.execSQL(create);
        }
        for (String insert: FAQDAO.insertFAQs()) {
            sqLiteDatabase.execSQL(insert);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void runNonSelectQuery(List<String> querys){
        this.dataBase = getWritableDatabase();

        try{
            for (String query: querys) {
                this.dataBase.execSQL(StringExtensions.substituirQuebrasDeLinha(query));
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }
    }

    @SuppressLint("Range")
    public Integer fetchLastId(String table){
        dataBase = getReadableDatabase();
        String sql = "SELECT id FROM " + table + " ORDER BY id";
        Integer id = 0;
        try {
            Cursor line = dataBase.rawQuery(sql, null);
            line.moveToLast();
            id = line.getInt(line.getColumnIndex("id"));
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }
        return id;
    }

}
