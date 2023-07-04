package com.example.amei.Modelos;

import com.google.gson.annotations.SerializedName;

public class QSA {
    private Integer id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("qual")
    private String qual;
    @SerializedName("pais_origem")
    private String pais_origem;
    @SerializedName("nome_rep_legal")
    private String nome_rep_legal;
    @SerializedName("qual_rep_legal")
    private String qual_rep_legal;


    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getPais_origem() {
        return pais_origem;
    }

    public void setPais_origem(String pais_origem) {
        this.pais_origem = pais_origem;
    }

    public String getNome_rep_legal() {
        return nome_rep_legal;
    }

    public void setNome_rep_legal(String nome_rep_legal) {
        this.nome_rep_legal = nome_rep_legal;
    }

    public String getQual_rep_legal() {
        return qual_rep_legal;
    }

    public void setQual_rep_legal(String qual_rep_legal) {
        this.qual_rep_legal = qual_rep_legal;
    }

    public String queryForInsert(){
        return  "'" + this.nome + "', '" + this.qual + "', '" + this.pais_origem + "', '" + nome_rep_legal + "', '" + qual_rep_legal + "'";
    }

}
