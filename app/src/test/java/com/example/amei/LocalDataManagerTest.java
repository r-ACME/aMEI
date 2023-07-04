package com.example.amei;

import android.content.Context;
import android.content.SharedPreferences;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.amei.Negocios.API.LocalDataManager;

public class LocalDataManagerTest {

    @Mock
    private Context context;

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor editor;

    private LocalDataManager localDataManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);
        when(sharedPreferences.edit()).thenReturn(editor);
        localDataManager = new LocalDataManager(context, "test_pref");
    }

    @Test
    public void testSaveString() {
        String data = "example data";
        String key = "test_key";

        localDataManager.saveString(data, key);

        verify(editor).remove(key);
        verify(editor).putString(key, data);
        verify(editor).apply();
    }

    @Test
    public void testRemoveAllPermissions() {
        String key = "test_key";

        localDataManager.removeAllPermissions(key);

        verify(editor).remove(key);
        verify(editor).apply();
    }

    @Test
    public void testGetString() {
        String data = "example data";
        String key = "test_key";

        when(sharedPreferences.getString(eq(key), anyString())).thenReturn(data);

        String result = localDataManager.getString(key);

        assertEquals(data, result);
    }

    @Test
    public void testHaveData() {
        String key = "test_key";

        when(sharedPreferences.getString(eq(key), anyString())).thenReturn("example data");

        boolean result = localDataManager.haveData(key);

        assertEquals(true, result);
    }
}
