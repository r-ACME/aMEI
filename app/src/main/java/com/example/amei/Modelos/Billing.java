package com.example.amei.Modelos;

import com.google.gson.annotations.SerializedName;

public class Billing {

    @SerializedName("free")
    private Boolean free;
    @SerializedName("database")
    private String database;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }
}
