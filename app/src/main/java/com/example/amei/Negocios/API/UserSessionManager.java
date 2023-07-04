package com.example.amei.Negocios.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.amei.Modelos.User;

public class UserSessionManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ID = "user_id";
    private static final String KEY_COMPANY_ID = "company_id";
    private static final String KEY_CNPJ = "cnpj";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;

    public UserSessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        removeAllPermissions();
        editor.putInt(KEY_ID, user.getId());
        editor.putInt(KEY_COMPANY_ID, user.getCompanyId());
        editor.putString(KEY_CNPJ, user.getCnpj());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.apply();
    }

    public void removeAllPermissions() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_ID);
        editor.remove(KEY_COMPANY_ID);
        editor.remove(KEY_CNPJ);
        editor.remove(KEY_PASSWORD);
        editor.apply();
    }

    public User getUser() {
        int id = sharedPreferences.getInt(KEY_ID, -1);
        int companyId = sharedPreferences.getInt(KEY_COMPANY_ID, -1);
        String cnpj = sharedPreferences.getString(KEY_CNPJ, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);

        if (id != -1 && companyId != -1 && cnpj != null && password != null) {
            User user = new User();
            user.setId(id);
            user.setCompanyId(companyId);
            user.setCnpj(cnpj);
            user.setPassword(password);
            return user;
        } else {
            return null;
        }
    }

    public boolean isUserLoggedIn() {
        return getUser() != null;
    }

    public void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
