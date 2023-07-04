package com.example.amei.Negocios.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class APIRetrofitCNPJ {

    private Retrofit retrofit;

    public APIRetrofitCNPJ() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://receitaws.com.br/v1/cnpj/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public APIInterfaceCNPJ getCNPJRest() {
        return this.retrofit.create(APIInterfaceCNPJ.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}