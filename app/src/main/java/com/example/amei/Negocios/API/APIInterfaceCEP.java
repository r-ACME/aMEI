package com.example.amei.Negocios.API;

        import com.example.amei.Modelos.CEPResponse;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;

public interface APIInterfaceCEP {

    @GET("{cep}")
    public Call<CEPResponse> getUser(@Path("cep") String cep);

}
