package com.example.amei;

import com.example.amei.Modelos.CEPResponse;
import com.example.amei.Negocios.API.APIInterfaceCEP;
import com.example.amei.Negocios.API.APIRetrofitCEP;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class APIRetrofitCEPTest {

    @Mock
    private Retrofit retrofit;

    @Mock
    private APIInterfaceCEP apiInterfaceCEP;

    private APIRetrofitCEP apiRetrofitCEP;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(retrofit.create(APIInterfaceCEP.class)).thenReturn(apiInterfaceCEP);
        apiRetrofitCEP = new APIRetrofitCEP(retrofit);
    }

    @Test
    public void testGetGitRest() {
        APIInterfaceCEP apiInterface = apiRetrofitCEP.getGitRest();
        assertNotNull(apiInterface);
        assertEquals(apiInterfaceCEP, apiInterface);
    }

    @Test
    public void testGetRetrofit() {
        Retrofit retrofitInstance = apiRetrofitCEP.getRetrofit();
        assertNotNull(retrofitInstance);
        assertEquals(retrofit, retrofitInstance);
    }
}

