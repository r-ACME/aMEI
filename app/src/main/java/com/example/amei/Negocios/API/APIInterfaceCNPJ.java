package com.example.amei.Negocios.API;

import com.example.amei.Modelos.CNPJResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterfaceCNPJ {

    @GET("{cnpj}")
    public Call<CNPJResponse> getUser(@Path("cnpj") String cnpj);

}


