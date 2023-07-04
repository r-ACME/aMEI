package com.example.amei;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.amei.Modelos.User;
import com.example.amei.Negocios.API.UserSessionManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class UserSessionManagerTest {

    @Mock
    private Context context;

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor editor;

    private UserSessionManager userSessionManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);
        when(sharedPreferences.edit()).thenReturn(editor);
        userSessionManager = new UserSessionManager(context);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1);
        user.setCompanyId(2);
        user.setCnpj("example_cnpj");
        user.setPassword("example_password");

        userSessionManager.saveUser(user);

        verify(editor).remove("user_id");
        verify(editor).remove("company_id");
        verify(editor).remove("cnpj");
        verify(editor).remove("password");
        verify(editor).putInt("user_id", 1);
        verify(editor).putInt("company_id", 2);
        verify(editor).putString("cnpj", "example_cnpj");
        verify(editor).putString("password", "example_password");
        verify(editor).apply();
    }

    @Test
    public void testRemoveAllPermissions() {
        userSessionManager.removeAllPermissions();

        verify(editor).remove("user_id");
        verify(editor).remove("company_id");
        verify(editor).remove("cnpj");
        verify(editor).remove("password");
        verify(editor).apply();
    }

    @Test
    public void testGetUser() {
        when(sharedPreferences.getInt(eq("user_id"), anyInt())).thenReturn(1);
        when(sharedPreferences.getInt(eq("company_id"), anyInt())).thenReturn(2);
        when(sharedPreferences.getString(eq("cnpj"), anyString())).thenReturn("example_cnpj");
        when(sharedPreferences.getString(eq("password"), anyString())).thenReturn("example_password");

        User user = userSessionManager.getUser();

        assertNotNull(user);
        assertEquals(Optional.of(1), user.getId());
        assertEquals(Optional.of(2), user.getCompanyId());
        assertEquals("example_cnpj", user.getCnpj());
        assertEquals("example_password", user.getPassword());
    }

    @Test
    public void testGetUser_Null() {
        when(sharedPreferences.getInt(eq("user_id"), anyInt())).thenReturn(-1);
        when(sharedPreferences.getInt(eq("company_id"), anyInt())).thenReturn(-1);
        when(sharedPreferences.getString(eq("cnpj"), anyString())).thenReturn(null);
        when(sharedPreferences.getString(eq("password"), anyString())).thenReturn(null);

        User user = userSessionManager.getUser();

        assertNull(user);
    }

    @Test
    public void testIsUserLoggedIn() {
        when(sharedPreferences.getInt(eq("user_id"), anyInt())).thenReturn(1);
        when(sharedPreferences.getInt(eq("company_id"), anyInt())).thenReturn(2);
        when(sharedPreferences.getString(eq("cnpj"), anyString())).thenReturn("example_cnpj");
        when(sharedPreferences.getString(eq("password"), anyString())).thenReturn("example_password");

        boolean isLoggedIn = userSessionManager.isUserLoggedIn();

        assertTrue(isLoggedIn);
    }

    @Test
    public void testIsUserLoggedIn_False() {
        when(sharedPreferences.getInt(eq("user_id"), anyInt())).thenReturn(-1);
        when(sharedPreferences.getInt(eq("company_id"), anyInt())).thenReturn(-1);
        when(sharedPreferences.getString(eq("cnpj"), anyString())).thenReturn(null);
        when(sharedPreferences.getString(eq("password"), anyString())).thenReturn(null);

        boolean isLoggedIn = userSessionManager.isUserLoggedIn();

        assertFalse(isLoggedIn);
    }

    @Test
    public void testLogoutUser() {
        userSessionManager.logoutUser();

        verify(editor).clear();
        verify(editor).apply();
    }
}
