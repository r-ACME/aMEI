package com.example.amei;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.User;
import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.Negocios.Banco.UserDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest {

    private UserDAO userDAO;

    @Mock
    private Context mockContext;

    @Mock
    private static SQLiteDatabase mockDatabase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAO(mockContext);
        userDAO.banco = new com.example.amei.Negocios.Banco.DataBase(mockContext);
    }

    @After
    public void tearDown() {
        userDAO = null;
    }

    @Test
    public void testGetAll() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, 1, "12345678901234", "password123"));
        expectedUsers.add(new User(2, 1, "98765432109876", "password456"));

        List<User> actualUsers = userDAO.getAll();

        Assert.assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            User expectedUser = expectedUsers.get(i);
            User actualUser = actualUsers.get(i);
            Assert.assertEquals(expectedUser.getId(), actualUser.getId());
            Assert.assertEquals(expectedUser.getCompanyId(), actualUser.getCompanyId());
            Assert.assertEquals(expectedUser.getCnpj(), actualUser.getCnpj());
            Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        }
    }

    @Test
    public void testInsertQuerys() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        List<String> expectedQueries = new ArrayList<>();
        expectedQueries.add("INSERT INTO users VALUES(1, 1, '12345678901234', 'password123')");
        expectedQueries.add("INSERT INTO users VALUES(2, 1, '98765432109876', 'password456')");

        List<String> actualQueries = userDAO.insertQuerys(users);

        Assert.assertEquals(expectedQueries.size(), actualQueries.size());
        for (int i = 0; i < expectedQueries.size(); i++) {
            Assert.assertEquals(expectedQueries.get(i), actualQueries.get(i));
        }
    }

    @Test
    public void testUserExists_existingUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User existingUser = new User(1, 1, "12345678901234", "password123");

        boolean userExists = userDAO.validateUser(existingUser, users);

        Assert.assertTrue(userExists);
    }

    @Test
    public void testUserExists_nonExistingUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User nonExistingUser = new User(3, 1, "11111111111111", "password789");

        boolean userExists = userDAO.validateUser(nonExistingUser, users);

        Assert.assertFalse(userExists);
    }

    @Test
    public void testValidateUser_validUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User validUser = new User(1, 1, "12345678901234", "password123");

        boolean isValidUser = UserDAO.validateUser(validUser, users);

        Assert.assertTrue(isValidUser);
    }

    @Test
    public void testValidateUser_invalidUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User invalidUser = new User(1, 1, "12345678901234", "wrongpassword");

        boolean isValidUser = UserDAO.validateUser(invalidUser, users);

        Assert.assertFalse(isValidUser);
    }

    @Test
    public void testGetValidUser_validUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User validUser = new User(1, 1, "12345678901234", "password123");

        User actualUser = UserDAO.getValidUser(validUser, users);

        Assert.assertEquals(validUser, actualUser);
    }

    @Test
    public void testGetValidUser_invalidUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, 1, "12345678901234", "password123"));
        users.add(new User(2, 1, "98765432109876", "password456"));

        User invalidUser = new User(1, 1, "12345678901234", "wrongpassword");

        User errorUser = new User();

        User actualUser = UserDAO.getValidUser(invalidUser, users);

        Assert.assertEquals(errorUser, actualUser);
    }

    // Helper class for mocking the database helper
    private static class MockDataBaseHelper extends DataBase {

        MockDataBaseHelper(Context context) {
            super(context);
        }

        @Override
        public SQLiteDatabase getReadableDatabase() {
            return mockDatabase;
        }

        @Override
        public SQLiteDatabase getWritableDatabase() {
            return mockDatabase;
        }

        // Implement other necessary overridden methods
    }
}
