package com.example.amei.Modelos;

public class Service {

    private Integer id;
    private String name;
    private String description;
    private String value;
    private String duration;
    private Integer type_id;

    public Service() {
    }

    public Service(Integer id, String name, String description, String value, String duration, Integer type_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.duration = duration;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String queryForInsert(){
        return "'" + this.name + "', '" + this.description + "', '" + this.value + "', '" + this.duration + "', " + this.type_id;
    }
}
