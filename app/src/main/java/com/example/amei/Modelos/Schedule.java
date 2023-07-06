package com.example.amei.Modelos;

import java.io.Serializable;

public class Schedule implements Serializable {

    private Integer id;
    private String datetime;
    private String title;
    private String description;
    private String alerts;
    private Integer client_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String queryForInsert(){
        return  "'" + this.datetime + "', '" + this.title + "', '" + this.description + "', '" + this.alerts + "', " + this.client_id;
    }

    public String queryForUpdate(){
        return  "UPDATE schedule SET datetime = '" + this.datetime +
                "',  title = '" + this.title +
                "', description = '" + this.description +
                "', alerts = '" + this.alerts +
                "', client_id = " + this.client_id +
                " WHERE id = " + this.id;
    }

    public Schedule(Integer id, String datetime, String title, String description, String alerts, Integer client_id) {
        this.id = id;
        this.datetime = datetime;
        this.title = title;
        this.description = description;
        this.alerts = alerts;
        this.client_id = client_id;
    }

    public Schedule(){

    }
}
