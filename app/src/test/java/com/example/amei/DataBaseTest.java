package com.example.amei;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.Negocios.Banco.FAQDAO;

public class DataBaseTest {

    @Mock
    private SQLiteDatabase mockDatabase;

    private DataBase dataBase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataBase = new DataBase(null);
    }

    @Test
    public void testOnCreate_CreatesTablesAndInsertsFAQs() {
        List<String> expectedCreates = new ArrayList<>();
        expectedCreates.add("CREATE TABLE faq(id int not null,question varchar(5000),answer varchar(100000),PRIMARY KEY (id));");
        expectedCreates.add("CREATE TABLE type(id int not null, name varchar(1000), expenses int, revenue int, PRIMARY KEY (id));");
        // Add other expected creates...

        List<String> expectedInserts = FAQDAO.insertFAQs();

        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        dataBase.onCreate(mockDatabase);

        verify(mockDatabase, times(expectedCreates.size())).execSQL(anyString());
        verify(mockDatabase, times(expectedInserts.size())).execSQL(anyString());

        // Verify each expected create statement
        for (String create : expectedCreates) {
            verify(mockDatabase).execSQL(create);
        }

        // Verify each expected insert statement
        for (String insert : expectedInserts) {
            verify(mockDatabase).execSQL(insert);
        }
    }

    @Test
    public void testRunNonSelectQuery_ExecutesNonSelectQueries() {
        List<String> queries = new ArrayList<>();
        queries.add("INSERT INTO table1 VALUES (1, 'value1')");
        queries.add("INSERT INTO table2 VALUES (2, 'value2')");
        // Add other queries...

        dataBase.dataBase = mockDatabase;

        dataBase.runNonSelectQuery(queries);

        verify(mockDatabase, times(queries.size())).execSQL(anyString());

        // Verify each query execution
        for (String query : queries) {
            verify(mockDatabase).execSQL(query);
        }

        verify(mockDatabase).close();
    }

    @Test
    public void testFetchLastId_ReturnsLastIdFromTable() {
        String table = "example_table";
        int expectedLastId = 10;

        Cursor mockCursor = mock(Cursor.class);
        when(mockCursor.moveToLast()).thenReturn(true);
        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("id")))).thenReturn(expectedLastId);

        when(mockDatabase.rawQuery(anyString(), any())).thenReturn(mockCursor);

        dataBase.dataBase = mockDatabase;

        int lastId = dataBase.fetchLastId(table);

        Assert.assertEquals(expectedLastId, lastId);

        verify(mockDatabase).rawQuery(anyString(), any());
        verify(mockCursor).moveToLast();
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("id")));
        verify(mockDatabase).close();
    }
}
