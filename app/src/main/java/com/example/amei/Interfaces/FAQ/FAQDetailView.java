package com.example.amei.Interfaces.FAQ;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amei.Modelos.FAQ;
import com.example.amei.Modelos.Schedule;
import com.example.amei.R;

public class FAQDetailView extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q_detail_view);

        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;

        Bundle receiveFAQ = getIntent().getExtras();
        FAQ currentFAQ;

        if (receiveFAQ != null) {
            currentFAQ = (FAQ) receiveFAQ.getSerializable("faqToShow");
        } else {
            currentFAQ = new FAQ(0,"", "");
        }

        TextView txtQuestion = findViewById(R.id.lbl_faq_detail_question);
        TextView txtAnswer = findViewById(R.id.lbl_faq_detail_answer);

        txtQuestion.setText(currentFAQ.getQuestion());
        txtAnswer.setText(currentFAQ.getAnswer());
    }
}