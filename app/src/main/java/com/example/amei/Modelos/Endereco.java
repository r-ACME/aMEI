package com.example.amei.Modelos;

public class Endereco {

    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;

    public Endereco() {
    }

    public Endereco(Integer id, String logradouro, String numero, String complemento, String cep, String bairro, String municipio, String uf) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void recieveFromCNPJResponse(CNPJResponse cnpj){
        this.uf = cnpj.getUf();
        this.municipio = cnpj.getMunicipio();
        this.bairro = cnpj.getBairro();
        this.complemento = cnpj.getComplemento();
        this.numero = cnpj.getNumero();
        this.logradouro = cnpj.getLogradouro();
        this.cep = cnpj.getCep();
    }

    public void recieveFromCEPResponse(CEPResponse cep){
        this.uf = cep.getState();
        this.municipio = cep.getCity();
        this.bairro = cep.getNeighborhood();
        this.logradouro = cep.getStreet();
        this.cep = cep.getCep();
    }

    public String queryForInsert(){
        return  "'" + this.logradouro + "', '" +
                this.numero + "', '" +
                this.complemento + "', '" +
                this.cep + "', '" +
                this.bairro + "', '" +
                this.municipio + "', '" +
                this.uf +  "'";
    }
}
