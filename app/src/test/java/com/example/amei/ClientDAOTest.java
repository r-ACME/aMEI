package com.example.amei;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amei.Modelos.Client;
import com.example.amei.Negocios.Banco.ClientDAO;
import com.example.amei.Negocios.Banco.DataBase;

public class ClientDAOTest {

    @Mock
    private DataBase mockDataBase;

    private ClientDAO clientDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        clientDAO = new ClientDAO(mockDataBase);
    }

    @Test
    public void testGetAll_ReturnsListOfClients() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getReadableDatabase()).thenReturn(mockDatabase);

        Cursor mockCursor = mock(Cursor.class);
        when(mockDatabase.rawQuery(anyString(), any())).thenReturn(mockCursor);

        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.moveToNext()).thenReturn(false);

        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("id")))).thenReturn(1);
        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("person_id")))).thenReturn(101);
        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("company_id")))).thenReturn(201);
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("type")))).thenReturn("Regular");
        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("active")))).thenReturn(1);

        List<Client> expectedClients = new ArrayList<>();
        Client expectedClient = new Client();
        expectedClient.setId(1);
        expectedClient.setPerson_id(101);
        expectedClient.setCompany_id(201);
        expectedClient.setType("Regular");
        expectedClient.setActive(1);
        expectedClients.add(expectedClient);

        List<Client> clients = clientDAO.getAll();

        Assert.assertEquals(expectedClients.size(), clients.size());
        Assert.assertEquals(expectedClients.get(0).getId(), clients.get(0).getId());
        Assert.assertEquals(expectedClients.get(0).getPerson_id(), clients.get(0).getPerson_id());
        Assert.assertEquals(expectedClients.get(0).getCompany_id(), clients.get(0).getCompany_id());
        Assert.assertEquals(expectedClients.get(0).getType(), clients.get(0).getType());
        Assert.assertEquals(expectedClients.get(0).getActive(), clients.get(0).getActive());

        verify(mockDataBase).getReadableDatabase();
        verify(mockDatabase).rawQuery(anyString(), any());
        verify(mockCursor).moveToFirst();
        verify(mockCursor).moveToNext();
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("id")));
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("person_id")));
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("company_id")));
        verify(mockCursor).getString(eq(mockCursor.getColumnIndex("type")));
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("active")));
    }

    @Test
    public void testInsertQuerys_ReturnsListOfQueries() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getWritableDatabase()).thenReturn(mockDatabase);

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        client1.setId(1);
        client1.setPerson_id(101);
        client1.setCompany_id(201);
        client1.setType("Regular");
        client1.setActive(1);
        clients.add(client1);

        when(mockDataBase.fetchLastId(eq("client"))).thenReturn(10);

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO client VALUES(11, '101', '201', 'Regular', 1)");

        List<String> queries = clientDAO.insertQuerys(clients);

        Assert.assertEquals(expectedQueries.size(), queries.size());
        Assert.assertEquals(expectedQueries.get(0), queries.get(0));

        verify(mockDataBase).getWritableDatabase();
        verify(mockDataBase).fetchLastId(eq("client"));
    }
}
