package com.example.amei.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amei.Negocios.API.UserSessionManager;
import com.example.amei.Negocios.Banco.UserDAO;
import com.example.amei.Modelos.User;
import com.example.amei.R;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserDAO userDAO = new UserDAO(Login.this);
        UserSessionManager manager = new UserSessionManager(Login.this);
        LinearLayoutCompat.LayoutParams wrap_content = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        LinearLayoutCompat.LayoutParams gone = new LinearLayoutCompat.LayoutParams(0,0);

        Button btnLogin = findViewById(R.id.btn_login_login);
        Button btnSignUp = findViewById(R.id.btn_signup);
        EditText txtLogin = findViewById(R.id.txt_login_login);
        EditText txtPassword = findViewById(R.id.txt_login_password);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currentUser = new User();
                currentUser.setCnpj(txtLogin.getText().toString());
                currentUser.setPassword(txtPassword.getText().toString());

                if (currentUser.getCnpj().length() != 14 || currentUser.getPassword().length() < 3) {
                    TextView txtErrorMessage = findViewById(R.id.lbl_login_error_message);
                    txtErrorMessage.setLayoutParams(wrap_content);
                } else {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            User currentUser = new User();
                            currentUser.setCnpj(txtLogin.getText().toString());
                            currentUser.setPassword(txtPassword.getText().toString());


                            TextView txtErrorMessage = findViewById(R.id.lbl_login_error_message);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    txtErrorMessage.setLayoutParams(gone);
                                }
                            });
                            List<User> users = new ArrayList<>();
                            if((userDAO.getAll().size() != 0)){
                                users = userDAO.getAll();

                                if (!UserDAO.validateUser(currentUser, userDAO.getAll())) { //usuário não existe ou não encontrado
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            txtErrorMessage.setLayoutParams(wrap_content);
                                        }
                                    });
                                } else {
                                    currentUser = UserDAO.getValidUser(currentUser, userDAO.getAll());
                                    manager.saveUser(currentUser);
                                    Intent intent = new Intent(Login.this, Main.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        txtErrorMessage.setLayoutParams(wrap_content);
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                }
            }
            //se falha:
//            editText.setInputType(InputType.TYPE_CLASS_TEXT); // ou outro tipo adequado
//            editText.setKeyListener(KeyListener.DEFAULT_KEY_LISTENER);
        });
    }
}