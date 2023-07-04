package com.example.amei.Modelos;

import com.example.amei.Negocios.StringExtensions;

import java.util.List;

public class User {

    private Integer id;
    private Integer companyId;
    private String cnpj;
    private String password;

    public User() {
    }

    public User(Integer id, Integer companyId, String cnpj, String password) {
        this.id = id;
        this.companyId = companyId;
        this.cnpj = cnpj;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String queryForInsert(){
        return this.companyId + ", '" + this.cnpj + "', '" + this.password + "'";
    }

    public void createFromCNPJResponse(CNPJResponse cnpj, String password){

        this.cnpj = StringExtensions.removeNonNumericCharacters(cnpj.getCnpj());
        this.password = password;
        this.companyId = cnpj.getId();
    }

}
