package com.example.amei.Modelos;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private String value;
    private String storage;
    private Integer type_id;

    public Product() {
    }

    public Product(Integer id, String name, String description, String value, String storage, Integer type_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.storage = storage;
        this.type_id = type_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String queryForInsert(){
        return "'" + this.name + "', '" + this.description + "', '" + this.value + "', '" + this.storage + "', " + this.type_id;
    }
}
