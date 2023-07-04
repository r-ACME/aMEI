package com.example.amei.Negocios.API;

        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;
public class APIRetrofitCEP {

    private Retrofit retrofit;

    public APIRetrofitCEP() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://brasilapi.com.br/api/cep/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public APIRetrofitCEP(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public APIInterfaceCEP getGitRest() {
        return this.retrofit.create(APIInterfaceCEP.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}