package com.example.amei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.amei.Modelos.CEPResponse;
import com.example.amei.Negocios.API.APIInterfaceCEP;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;

public class APIInterfaceCEPTest {

    @Mock
    private Call<CEPResponse> call;

    private APIInterfaceCEP apiInterfaceCEP;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        apiInterfaceCEP = new APIInterfaceCEP() {
            @Override
            public Call<CEPResponse> getUser(String cep) {
                return call;
            }
        };
    }

    @Test
    public void testGetUser() {
        String cep = "12345678";
        Call<CEPResponse> callInstance = apiInterfaceCEP.getUser(cep);
        assertNotNull(callInstance);
        assertEquals(call, callInstance);
    }
}
