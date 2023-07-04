package com.example.amei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.amei.Modelos.Schedule;
import com.example.amei.Negocios.Banco.SchedulesDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class SchedulesDAOTest {

    private SchedulesDAO schedulesDAO;

    @Mock
    private Context mockContext;

    @Mock
    private SQLiteDatabase mockDatabase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        schedulesDAO = new SchedulesDAO(mockContext);
        schedulesDAO.banco = new com.example.amei.Negocios.Banco.DataBase(mockContext);
    }

    @After
    public void tearDown() {
        schedulesDAO = null;
    }

    @Test
    public void testGetAll() {
        List<Schedule> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(new Schedule(1, "2023-07-04 10:00", "Meeting", "Discuss project details", "10 minutes", 1));
        expectedSchedules.add(new Schedule(2, "2023-07-05 14:30", "Appointment", "Doctor's appointment", "1 hour", 2));

        List<Schedule> actualSchedules = schedulesDAO.getAll();

        Assert.assertEquals(expectedSchedules.size(), actualSchedules.size());
        for (int i = 0; i < expectedSchedules.size(); i++) {
            Schedule expectedSchedule = expectedSchedules.get(i);
            Schedule actualSchedule = actualSchedules.get(i);
            Assert.assertEquals(expectedSchedule.getId(), actualSchedule.getId());
            Assert.assertEquals(expectedSchedule.getDatetime(), actualSchedule.getDatetime());
            Assert.assertEquals(expectedSchedule.getTitle(), actualSchedule.getTitle());
            Assert.assertEquals(expectedSchedule.getDescription(), actualSchedule.getDescription());
            Assert.assertEquals(expectedSchedule.getAlerts(), actualSchedule.getAlerts());
            Assert.assertEquals(expectedSchedule.getClient_id(), actualSchedule.getClient_id());
        }
    }

    @Test
    public void testInsertQuerys() {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(1, "2023-07-04 10:00", "Meeting", "Discuss project details", "10 minutes", 1));
        schedules.add(new Schedule(2, "2023-07-05 14:30", "Appointment", "Doctor's appointment", "1 hour", 2));

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO schedule VALUES(1, '2023-07-04 10:00', 'Meeting', 'Discuss project details', '10 minutes', 1)");
        expectedQueries.add("INSERT INTO schedule VALUES(2, '2023-07-05 14:30', 'Appointment', 'Doctor's appointment', '1 hour', 2)");

        List<String> actualQueries = schedulesDAO.insertQuerys(schedules);

        Assert.assertEquals(expectedQueries.size(), actualQueries.size());
        for (int i = 0; i < expectedQueries.size(); i++) {
            Assert.assertEquals(expectedQueries.get(i), actualQueries.get(i));
        }
    }

    @Test
    public void testUpdateQuerys() {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(new Schedule(1, "2023-07-04 10:00", "Meeting", "Discuss project details", "10 minutes", 1));
        schedules.add(new Schedule(2, "2023-07-05 14:30", "Appointment", "Doctor's appointment", "1 hour", 2));

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("UPDATE schedule SET datetime = '2023-07-04 10:00', title = 'Meeting', description = 'Discuss project details', alerts = '10 minutes', client_id = 1 WHERE id = 1");
        expectedQueries.add("UPDATE schedule SET datetime = '2023-07-05 14:30', title = 'Appointment', description = 'Doctor's appointment', alerts = '1 hour', client_id = 2 WHERE id = 2");

        List<String> actualQueries = schedulesDAO.updateQuerys(schedules);

        Assert.assertEquals(expectedQueries.size(), actualQueries.size());
        for (int i = 0; i < expectedQueries.size(); i++) {
            Assert.assertEquals(expectedQueries.get(i), actualQueries.get(i));
        }
    }

    // Helper class for mocking the database helper
    private static class MockDataBaseHelper extends SQLiteOpenHelper {

        MockDataBaseHelper(Context context) {
            super(context, "mock_database", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create table statements
            db.execSQL("CREATE TABLE schedule (id INTEGER PRIMARY KEY, datetime TEXT, title TEXT, description TEXT, alerts TEXT, client_id INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Not implemented in this example
        }
    }
}
