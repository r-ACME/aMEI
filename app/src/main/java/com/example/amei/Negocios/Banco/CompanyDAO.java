package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.Atividade;
import com.example.amei.Modelos.Company;
import com.example.amei.Modelos.QSA;

import java.util.ArrayList;
import java.util.List;

public class CompanyDAO{

    DataBase banco;

    public CompanyDAO(Context context) {
        this.banco = new DataBase(context);
    }

    public CompanyDAO(DataBase dataBase) {
        this.banco = dataBase;
    }

    @SuppressLint("Range")
    public List<Company> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();

        List<Company> companys = new ArrayList<>();

        try{
            Cursor line = dataBase.rawQuery("SELECT * FROM company", null);

            if(line.moveToFirst()){
                do{
                    Company company = new Company();
                    company.setId(line.getInt(line.getColumnIndex("id")));

                    company.setAbertura(line.getString(line.getColumnIndex("abertura")));
                    company.setCnpj(line.getString(line.getColumnIndex("cnpj")));
//                    company.setAtividade_principal(line.getString(line.getColumnIndex("atividade_principal")));
//                    company.setAtividades_secundarias(line.getString(line.getColumnIndex("atividades_secundarias")));
                    company.setCapital_social(line.getString(line.getColumnIndex("capital_social")));
                    company.setData_situacao(line.getString(line.getColumnIndex("data_situacao")));
                    company.setData_situacao_especial(line.getString(line.getColumnIndex("data_situacao_especial")));
                    company.setEfr(line.getString(line.getColumnIndex("efr")));
                    company.setEmail(line.getString(line.getColumnIndex("email")));
                    company.setFantasia(line.getString(line.getColumnIndex("fantasia")));
                    company.setMotivo_situacao(line.getString(line.getColumnIndex("motivo_situacao")));
                    company.setNatureza_juridica(line.getString(line.getColumnIndex("natureza_juridica")));
                    company.setNome(line.getString(line.getColumnIndex("nome")));
                    company.setPorte(line.getString(line.getColumnIndex("porte")));
//                    company.setQsa(line.getString(line.getColumnIndex("qsa")));
                    company.setSituacao(line.getString(line.getColumnIndex("situacao")));
                    company.setSituacao_especial(line.getString(line.getColumnIndex("situacao_especial")));
                    company.setStatus(line.getString(line.getColumnIndex("status")));
                    company.setTelefone(line.getString(line.getColumnIndex("telefone")));
                    company.setTipo(line.getString(line.getColumnIndex("tipo")));
                    company.setUltima_atualizacao(line.getString(line.getColumnIndex("ultima_atualizacao")));
                    company.setAdressid(line.getInt(line.getColumnIndex("adressid")));

                    companys.add(company);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return companys;
    }

    public Integer fetchLastId(String table){
        return banco.fetchLastId(table);
    }

    public List<String> insertQuerys(List<Company> companys){
        List<String> querys = new ArrayList<>();
        SQLiteDatabase dataBase = banco.getWritableDatabase();

        try{
            for (Company company : companys){
                Integer currentId = banco.fetchLastId("company") + 1;
                querys.add("INSERT INTO company VALUES(" + currentId + ", " + company.queryForInsert() + ")");
                for (String query : subQuerys(company)) {
                    querys.add(query);
                }
            }
        }catch (Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return querys;
    }

    public List<String> subQuerys(Company company){
        List<String> querys = new ArrayList<>();
        Integer id = 0;
        id = banco.fetchLastId("activity");
        for(Atividade atividade : company.getAtividade_principal()){
            querys.add("INSERT INTO activity VALUES ( " + id + ", " + atividade.queryForInsert() + ", 'primary');");
            id++;
        }
        for(Atividade atividade : company.getAtividades_secundarias()){
            querys.add("INSERT INTO activity VALUES ( " + id + ", " + atividade.queryForInsert() + ", 'secondary');");
            id++;
        }
        id = banco.fetchLastId("qsa");
        for (QSA qsa : company.getQsa()){
            querys.add("INSERT INTO qsa VALUES ( " + id + ", " + qsa.queryForInsert() + ");");
            querys.add("INSERT INTO company_qsa VALUES ( " + company.getId() + ", " + id + ");");
        }


        return querys;
    }

    public Boolean isMEI(Company company){
        if(company.getNatureza_juridica().contains("213-5")){
            return true;
        }
        return false;
    }
}
