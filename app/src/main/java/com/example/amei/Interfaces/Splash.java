package com.example.amei.Interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.amei.Negocios.API.UserSessionManager;
import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.R;


public class Splash extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        UserSessionManager validade = new UserSessionManager(this);

        if(validade.isUserLoggedIn()){
            Intent intent = new Intent(Splash.this, Main.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(Splash.this, Login.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DataBase db = new DataBase(this);

        UserSessionManager validade = new UserSessionManager(this);

        if(validade.isUserLoggedIn()){
            Intent intent = new Intent(Splash.this, Main.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(Splash.this, Login.class);
            startActivity(intent);
        }

//        AlertDialog.Builder about = new AlertDialog.Builder(Splash.this);
//
//        about.setMessage("Deseja utilizar a ultima sessão?");
//        about.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(Splash.this, Main.class);
//                startActivity(intent);
//            }
//        });
//        about.setNegativeButton("Não", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i){
//                //ir para login
//
//                Intent intent = new Intent(Splash.this, Login.class);
//                startActivity(intent);
//            }
//        });
//
//        AlertDialog dialog = about.create();
//        dialog.show();


    }
}