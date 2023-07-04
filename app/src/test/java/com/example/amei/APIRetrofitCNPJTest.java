package com.example.amei;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.amei.Modelos.CNPJResponse;
import com.example.amei.Negocios.API.APIInterfaceCNPJ;
import com.example.amei.Negocios.API.APIRetrofitCNPJ;

public class APIRetrofitCNPJTest {

    @Mock
    private Retrofit retrofit;

    @Mock
    private APIInterfaceCNPJ apiInterface;

    private APIRetrofitCNPJ apiRetrofitCNPJ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(retrofit.create(APIInterfaceCNPJ.class)).thenReturn(apiInterface);
        apiRetrofitCNPJ = new APIRetrofitCNPJ();
    }

    @Test
    public void testGetCNPJRest() {
        APIInterfaceCNPJ result = apiRetrofitCNPJ.getCNPJRest();
        assertEquals(apiInterface, result);
    }

    @Test
    public void testGetUser() throws IOException {
        String cnpj = "1234567890";
        Call<CNPJResponse> call = mock(Call.class);
        CNPJResponse cnpjResponse = new CNPJResponse();

        when(apiInterface.getUser(cnpj)).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(cnpjResponse));

        CNPJResponse result = apiRetrofitCNPJ.getCNPJRest().getUser(cnpj).execute().body();
        assertEquals(cnpjResponse, result);

        verify(apiInterface).getUser(cnpj);
        verify(call).execute();
    }
}
