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

import com.example.amei.Modelos.Atividade;
import com.example.amei.Negocios.Banco.AtividadeDAO;
import com.example.amei.Negocios.Banco.DataBase;

public class AtividadeDAOTest {

    @Mock
    private DataBase mockDataBase;

    private AtividadeDAO atividadeDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        atividadeDAO = new AtividadeDAO(mockDataBase);
    }

    @Test
    public void testGetAll_ReturnsListOfActivities() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getReadableDatabase()).thenReturn(mockDatabase);

        Cursor mockCursor = mock(Cursor.class);
        when(mockDatabase.rawQuery(anyString(), any())).thenReturn(mockCursor);

        when(mockCursor.moveToFirst()).thenReturn(true);
        when(mockCursor.moveToNext()).thenReturn(false);

        when(mockCursor.getInt(eq(mockCursor.getColumnIndex("id")))).thenReturn(1);
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("code")))).thenReturn("ACT001");
        when(mockCursor.getString(eq(mockCursor.getColumnIndex("text")))).thenReturn("Activity 1");

        List<Atividade> expectedActivities = new ArrayList<>();
        Atividade expectedActivity = new Atividade();
        expectedActivity.setId(1);
        expectedActivity.setCode("ACT001");
        expectedActivity.setText("Activity 1");
        expectedActivities.add(expectedActivity);

        List<Atividade> activities = atividadeDAO.getAll(1);

        Assert.assertEquals(expectedActivities.size(), activities.size());
        Assert.assertEquals(expectedActivities.get(0).getId(), activities.get(0).getId());
        Assert.assertEquals(expectedActivities.get(0).getCode(), activities.get(0).getCode());
        Assert.assertEquals(expectedActivities.get(0).getText(), activities.get(0).getText());

        verify(mockDataBase).getReadableDatabase();
        verify(mockDatabase).rawQuery(anyString(), any());
        verify(mockCursor).moveToFirst();
        verify(mockCursor).moveToNext();
        verify(mockCursor).getInt(eq(mockCursor.getColumnIndex("id")));
        verify(mockCursor).getString(eq(mockCursor.getColumnIndex("code")));
        verify(mockCursor).getString(eq(mockCursor.getColumnIndex("text")));
    }

    @Test
    public void testInsertQuerys_ReturnsListOfQueries() {
        SQLiteDatabase mockDatabase = mock(SQLiteDatabase.class);
        when(mockDataBase.getWritableDatabase()).thenReturn(mockDatabase);

        List<Atividade> activities = new ArrayList<>();
        Atividade activity1 = new Atividade();
        activity1.setId(1);
        activity1.setCode("ACT001");
        activity1.setText("Activity 1");
        activities.add(activity1);

        when(mockDataBase.fetchLastId(eq("activity"))).thenReturn(10);

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO activity VALUES(11, 'ACT001', 'Activity 1')");
        expectedQueries.add("INSERT INTO company_activities VALUES(121)");

        List<String> queries = atividadeDAO.insertQuerys(12, activities);

        Assert.assertEquals(expectedQueries.size(), queries.size());
        Assert.assertEquals(expectedQueries.get(0), queries.get(0));
        Assert.assertEquals(expectedQueries.get(1), queries.get(1));

        verify(mockDataBase).getWritableDatabase();
        verify(mockDataBase).fetchLastId(eq("activity"));
    }
}
