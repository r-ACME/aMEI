package com.example.amei.Modelos;

import java.util.ArrayList;

import com.example.amei.Negocios.StringExtensions;
import com.google.gson.annotations.SerializedName;

public class Company {

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
        this.cnpj = StringExtensions.removeNonNumericCharacters(cnpj);
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

    public Integer getAdressid() {
        return adressid;
    }

    public void setAdressid(Integer adressid) {
        this.adressid = adressid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
    @SerializedName("adressid")
    private Integer adressid;
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

    public void recieveFromCNPJResponse(CNPJResponse cnpj, Integer adressid){
        this.abertura = cnpj.getAbertura();
        this.cnpj = StringExtensions.removeNonNumericCharacters(cnpj.getCnpj());
        this.atividade_principal = cnpj.getAtividade_principal();
        this.billing = cnpj.getBilling();
        this.atividades_secundarias = cnpj.getAtividades_secundarias();
        this.capital_social = cnpj.getCapital_social();
        this.data_situacao = cnpj.getData_situacao();
        this.data_situacao_especial = cnpj.getData_situacao_especial();
        this.efr = cnpj.getEfr();
        this.email = cnpj.getEmail();
        this.fantasia = cnpj.getFantasia();
        this.motivo_situacao = cnpj.getMotivo_situacao();
        this.natureza_juridica = cnpj.getNatureza_juridica();
        this.nome = cnpj.getNome();
        this.porte = cnpj.getPorte();
        this.qsa = cnpj.getQsa();
        this.situacao = cnpj.getSituacao();
        this.situacao_especial = cnpj.getSituacao_especial();
        this.status = cnpj.getStatus();
        this.telefone = cnpj.getTelefone();
        this.tipo = cnpj.getTipo();
        this.ultima_atualizacao = cnpj.getUltima_atualizacao();

        this.adressid = adressid;
    }

    public String queryForInsert() {
        return  "'" + this.status + "', '" +
                this.ultima_atualizacao + "', '" +
                this.cnpj + "', '" +
                this.tipo + "', '" +
                this.porte + "', '" +
                this.nome + "', '" +
                this.fantasia + "', '" +
                this.abertura + "', '" +
                this.natureza_juridica + "', " +
                this.adressid + ", '" +
                this.email + "', '" +
                this.telefone + "', '" +
                this.efr + "', '" +
                this.situacao + "', '" +
                this.data_situacao + "', '" +
                this.motivo_situacao + "', '" +
                this.situacao_especial + "', '" +
                this.data_situacao_especial + "', '" +
                this.capital_social + "'";
    }

}

