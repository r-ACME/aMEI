package com.example.amei.Modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CNPJResponse {

    @SerializedName("id")
    private Integer id;
    @SerializedName("status")
    private String status;
    @SerializedName("ultima_atualizacao")
    private String ultima_atualizacao;
    @SerializedName("cnpj")
    private String cnpj;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("porte")
    private String porte;
    @SerializedName("nome")
    private String nome;
    @SerializedName("fantasia")
    private String fantasia;
    @SerializedName("abertura")
    private String abertura;
    @SerializedName("atividade_principal")
    private ArrayList<Atividade> atividade_principal;
    @SerializedName("atividades_secundarias")
    private ArrayList<Atividade> atividades_secundarias;
    @SerializedName("natureza_juridica")
    private String natureza_juridica;
    @SerializedName("logradouro")
    private String logradouro;
    @SerializedName("numero")
    private String numero;
    @SerializedName("complemento")
    private String complemento;
    @SerializedName("cep")
    private String cep;
    @SerializedName("bairro")
    private String bairro;
    @SerializedName("municipio")
    private String municipio;
    @SerializedName("uf")
    private String uf;
    @SerializedName("email")
    private String email;
    @SerializedName("telefone")
    private String telefone;
    @SerializedName("efr")
    private String efr;
    @SerializedName("situacao")
    private String situacao;
    @SerializedName("data_situacao")
    private String data_situacao;
    @SerializedName("motivo_situacao")
    private String motivo_situacao;
    @SerializedName("situacao_especial")
    private String situacao_especial;
    @SerializedName("data_situacao_especial")
    private String data_situacao_especial;
    @SerializedName("capital_social")
    private String capital_social;
    @SerializedName("qsa")
    private ArrayList<QSA> qsa;
    @SerializedName("billing")
    private Billing billing;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(String ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public ArrayList<Atividade> getAtividade_principal() {
        return atividade_principal;
    }

    public void setAtividade_principal(ArrayList<Atividade> atividade_principal) {
        this.atividade_principal = atividade_principal;
    }

    public ArrayList<Atividade> getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(ArrayList<Atividade> atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public String getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_situacao() {
        return data_situacao;
    }

    public void setData_situacao(String data_situacao) {
        this.data_situacao = data_situacao;
    }

    public String getMotivo_situacao() {
        return motivo_situacao;
    }

    public void setMotivo_situacao(String motivo_situacao) {
        this.motivo_situacao = motivo_situacao;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(String capital_social) {
        this.capital_social = capital_social;
    }

    public ArrayList<QSA> getQsa() {
        return qsa;
    }

    public void setQsa(ArrayList<QSA> qsa) {
        this.qsa = qsa;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

}
