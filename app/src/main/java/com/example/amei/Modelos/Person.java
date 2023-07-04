package com.example.amei.Modelos;

public class Person {

    private Integer id;
    private Integer adress_id;
    private String name;
    private String document;
    private String phone;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(Integer adress_id) {
        this.adress_id = adress_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String queryForInsert(){
        return this.adress_id + ", '" + this.name + "', '" + this.document + "', '" + this.phone + "', '" + this.email +  "'";
    }
}
