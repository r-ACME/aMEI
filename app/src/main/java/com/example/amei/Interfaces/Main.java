package com.example.amei.Interfaces;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.amei.Negocios.API.UserSessionManager;
import com.example.amei.Interfaces.Schedule.ScheduleView;
import com.example.amei.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class Main extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        RelativeLayout.LayoutParams match_parent = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            match_parent.addRule(RelativeLayout.RIGHT_OF, R.id.sv_menu_bar);
        }
        else{
            match_parent.addRule(RelativeLayout.ABOVE, R.id.sv_menu_bar);
        }
        RelativeLayout.LayoutParams gone = new RelativeLayout.LayoutParams(0,0);


        ImageButton btnSchedule = findViewById(R.id.btn_main_schedule);
        ImageButton btnClient = findViewById(R.id.btn_main_clients);
        ImageButton btnProducts = findViewById(R.id.btn_main_products);
        ImageButton btnAccounting = findViewById(R.id.btn_main_accounting);
        ImageButton btnRH = findViewById(R.id.btn_main_rh);
        ImageButton btnRegisters = findViewById(R.id.btn_main_register);
        ImageButton btnFAQ = findViewById(R.id.btn_main_faq);
        ImageButton btnContactUs = findViewById(R.id.btn_main_contact_us);
        ImageButton btnAbout = findViewById(R.id.btn_main_about);
        ImageButton btnLogout = findViewById(R.id.btn_main_logout);

        FragmentContainerView fcvSchedule = findViewById(R.id.frag_schedule);
        fcvSchedule.setLayoutParams(match_parent);
        FragmentContainerView fcvClient = findViewById(R.id.frag_client);
        FragmentContainerView fcvProducts = findViewById(R.id.frag_products);
        FragmentContainerView fcvAccounting = findViewById(R.id.frag_accounting);
        FragmentContainerView fcvRH = findViewById(R.id.frag_rh);
        FragmentContainerView fcvRegister = findViewById(R.id.frag_registers);
        FragmentContainerView fcvFAQ = findViewById(R.id.frag_faq);
        FragmentContainerView fcvContactUs = findViewById(R.id.frag_contact_us);
        FragmentContainerView fcvAbout = findViewById(R.id.frag_about);
        FragmentContainerView fcvUpdate = findViewById(R.id.frag_wait_update);

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.white);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(match_parent);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(gone);
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.white);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnProducts.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.white);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnAccounting.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.white);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnRH.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.white);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnRegisters.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.white);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnFAQ.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.white);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(match_parent);
                Fragment fUpdate = new WaitForUpdate();
                replaceFragment(fUpdate);
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.white);
                btnAbout.setBackgroundResource(R.color.gray);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(match_parent);
                fcvAbout.setLayoutParams(gone);
                fcvUpdate.setLayoutParams(gone);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                btnSchedule.setBackgroundResource(R.color.gray);
                btnClient.setBackgroundResource(R.color.gray);
                btnProducts.setBackgroundResource(R.color.gray);
                btnAccounting.setBackgroundResource(R.color.gray);
                btnRH.setBackgroundResource(R.color.gray);
                btnRegisters.setBackgroundResource(R.color.gray);
                btnFAQ.setBackgroundResource(R.color.gray);
                btnContactUs.setBackgroundResource(R.color.gray);
                btnAbout.setBackgroundResource(R.color.white);
                btnLogout.setBackgroundResource(R.color.gray);

                fcvSchedule.setLayoutParams(gone);
                fcvClient.setLayoutParams(gone);
                fcvProducts.setLayoutParams(gone);
                fcvAccounting.setLayoutParams(gone);
                fcvRH.setLayoutParams(gone);
                fcvRegister.setLayoutParams(gone);
                fcvFAQ.setLayoutParams(gone);
                fcvContactUs.setLayoutParams(gone);
                fcvAbout.setLayoutParams(match_parent);
                fcvUpdate.setLayoutParams(gone);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                UserSessionManager sessionManager = new UserSessionManager(Main.this);
                sessionManager.logoutUser();

                // Reinicia o aplicativo
                Intent intent = new Intent(Main.this, Splash.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // Finaliza a atividade atual
                if (Main.this instanceof Activity) {
                    ((Activity) Main.this).finish();
                }
            }
        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_wait_update, fragment);
        fragmentTransaction.commit();
    }
}