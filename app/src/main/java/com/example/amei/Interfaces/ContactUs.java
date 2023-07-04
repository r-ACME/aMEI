package com.example.amei.Interfaces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amei.R;

public class ContactUs extends Fragment {

    private EditText etSubject;
    private EditText etMessage;
    private Button btnSend;

    public ContactUs() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

        etSubject = view.findViewById(R.id.et_subject);
        etMessage = view.findViewById(R.id.et_message);
        btnSend = view.findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return view;
    }

    private void sendEmail() {
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Enviar e-mail"));
        } else {
            Toast.makeText(getActivity(), "Nenhum cliente de e-mail instalado", Toast.LENGTH_SHORT).show();
        }
    }
}
