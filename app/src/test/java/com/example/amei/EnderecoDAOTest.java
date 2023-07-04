package com.example.amei;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.amei.Modelos.Endereco;
import com.example.amei.Negocios.Banco.EnderecoDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EnderecoDAOTest {

    @Mock
    private SQLiteDatabase mockDatabase;

    @Mock
    private Cursor mockCursor;

    private EnderecoDAO enderecoDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        enderecoDAO = new EnderecoDAO(null);
    }

    @Test
    public void testGetAll_ReturnsAllEnderecosFromTable() {
        List<Endereco> expectedEnderecos = new ArrayList<>();
        expectedEnderecos.add(new Endereco(1, "Rua A", "123", "Apt 1", "12345-678", "Bairro A", "Cidade A", "UF A"));
        expectedEnderecos.add(new Endereco(2, "Rua B", "456", "Apt 2", "98765-432", "Bairro B", "Cidade B", "UF B"));

        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.moveToNext()).thenReturn(true, true, false);

        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("id")))).thenReturn(1, 2);
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("logradouro")))).thenReturn("Rua A", "Rua B");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("numero")))).thenReturn("123", "456");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("complemento")))).thenReturn("Apt 1", "Apt 2");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("cep")))).thenReturn("12345-678", "98765-432");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("bairro")))).thenReturn("Bairro A", "Bairro B");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("municipio")))).thenReturn("Cidade A", "Cidade B");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("uf")))).thenReturn("UF A", "UF B");

        when(mockDatabase.rawQuery(anyString(), any())).thenReturn(mockCursor);

        enderecoDAO.banco.dataBase = mockDatabase;

        List<Endereco> enderecos = enderecoDAO.getAll();

        Assert.assertEquals(expectedEnderecos.size(), enderecos.size());
        Assert.assertEquals(expectedEnderecos.get(0).getId(), enderecos.get(0).getId());
        Assert.assertEquals(expectedEnderecos.get(1).getLogradouro(), enderecos.get(1).getLogradouro());
        // Assert other properties...

        verify(mockDatabase).rawQuery(anyString(), any());
        verify(mockCursor).moveToFirst();
        verify(mockCursor, times(2)).moveToNext();
        verify(mockCursor, times(expectedEnderecos.size())).getInt(eq(mockCursor.getColumnIndex("id")));
        verify(mockCursor, times(expectedEnderecos.size())).getString(eq(mockCursor.getColumnIndex("logradouro")));
        // Verify other cursor operations...
        verify(mockDatabase).close();
    }

    @Test
    public void testInsertQuerys_ReturnsInsertQueriesForEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco(1, "Rua A", "123", "Apt 1", "12345-678", "Bairro A", "Cidade A", "UF A"));
        enderecos.add(new Endereco(2, "Rua B", "456", "Apt 2", "98765-432", "Bairro B", "Cidade B", "UF B"));

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO adress VALUES(1, 'Rua A', '123', 'Apt 1', '12345-678', 'Bairro A', 'Cidade A', 'UF A')");
        expectedQueries.add("INSERT INTO adress VALUES(2, 'Rua B', '456', 'Apt 2', '98765-432', 'Bairro B', 'Cidade B', 'UF B')");

        int lastId = 0;

        doReturn(lastId).when(enderecoDAO.banco).fetchLastId(eq("adress"));

        List<String> queries = enderecoDAO.insertQuerys(enderecos);

        Assert.assertEquals(expectedQueries.size(), queries.size());
        Assert.assertEquals(expectedQueries.get(0), queries.get(0));
        Assert.assertEquals(expectedQueries.get(1), queries.get(1));

        verify(enderecoDAO.banco).fetchLastId(eq("adress"));
        verify(enderecoDAO.banco).getWritableDatabase();
        verify(enderecoDAO.banco).close();
    }
}
