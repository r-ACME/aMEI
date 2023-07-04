package com.example.amei.Modelos;

import com.google.gson.annotations.SerializedName;

public class Atividade {

    private Integer id;
    @SerializedName("code")
    private String code;
    @SerializedName("text")
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String queryForInsert(){
        return "'" + this.code + "', '" + this.text + "'";
    }
}
