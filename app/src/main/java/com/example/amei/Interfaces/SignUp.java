package com.example.amei.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amei.Negocios.API.APIInterfaceCNPJ;
import com.example.amei.Negocios.API.APIRetrofitCNPJ;
import com.example.amei.Negocios.API.UserSessionManager;
import com.example.amei.Negocios.Banco.CompanyDAO;
import com.example.amei.Negocios.Banco.DataBase;
import com.example.amei.Negocios.Banco.EnderecoDAO;
import com.example.amei.Negocios.Banco.UserDAO;
import com.example.amei.Modelos.CNPJResponse;
import com.example.amei.Modelos.Company;
import com.example.amei.Modelos.Endereco;
import com.example.amei.Modelos.User;
import com.example.amei.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    CNPJResponse procura;
    List<Endereco> adressToInsert;
    List<Company> companyToInsert;
    List<User> userToInsert;

    Boolean canCreate = true;

    DataBase db;
    UserDAO userDAO;
    EnderecoDAO enderecoDAO;
    CompanyDAO companyDAO;
    Company company;


    Endereco adress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        User currentUser = new User();
        enderecoDAO = new EnderecoDAO(SignUp.this);
        companyDAO = new CompanyDAO(SignUp.this);
        userDAO = new UserDAO(SignUp.this);
        company = new Company();
        adress = new Endereco();
        db = new DataBase(this);

        LinearLayoutCompat llPassword = findViewById(R.id.ll_signup_password);
        LinearLayoutCompat llSignUp = findViewById(R.id.ll_signup_signup);
        LinearLayoutCompat llFetchCNPJ = findViewById(R.id.ll_fetch_cnpj);

        Button btnFetchDocument = findViewById(R.id.btn_fetch_document);
        Button btnSignUp = findViewById(R.id.btn_signup_signup);

        EditText txtCNPJ = findViewById(R.id.txt_signup_cnpj);
        EditText txtPassword = findViewById(R.id.txt_signup_password);

        btnFetchDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentUser.setCnpj(txtCNPJ.getText().toString());

                if (userDAO.userExists(currentUser)) {
                    Toast.makeText(SignUp.this, "CNPJ já cadastrado: " + txtCNPJ.getText().toString(), Toast.LENGTH_LONG).show();
                    canCreate = false;
                }

                if (canCreate) {

                    APIRetrofitCNPJ gitRest = new APIRetrofitCNPJ();

                    APIInterfaceCNPJ service = gitRest.getCNPJRest();
                    Call<CNPJResponse> fetchUser = service.getUser(currentUser.getCnpj());

                    fetchUser.enqueue(new Callback<CNPJResponse>() {
                        @Override
                        public void onResponse(Call<CNPJResponse> call, Response<CNPJResponse> response) {
                            procura = response.body();

                            if (procura.getStatus().equals("ERROR")) {
                                Toast.makeText(SignUp.this, "CNPJ não encontrado: " + currentUser.getCnpj(), Toast.LENGTH_LONG).show();
                            } else {
                                adress.recieveFromCNPJResponse(procura);
                                adress.setId(db.fetchLastId("adress") + 1);
                                company.setId(db.fetchLastId("company") + 1);
                                company.recieveFromCNPJResponse(procura, adress.getId());
                                if (companyDAO.isMEI(company)) {
                                    //Toast.makeText(SignUp.this, "CNPJ encontrado: " + procura.getNome(),  Toast.LENGTH_LONG).show();

                                    llPassword.setVisibility(View.VISIBLE);
                                    llSignUp.setVisibility(View.VISIBLE);
                                    llFetchCNPJ.setVisibility(View.GONE);
                                    txtCNPJ.setEnabled(false);
                                } else {
                                    Toast.makeText(SignUp.this, "CNPJ encontrado não é do tipo MEI " + procura.getCnpj(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CNPJResponse> call, Throwable t) {
                            Toast.makeText(SignUp.this, "CNPJ não encontrado: " + txtCNPJ.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adressToInsert = new ArrayList<>();
                companyToInsert = new ArrayList<>();
                userToInsert = new ArrayList<>();

                adressToInsert.add(adress);
                companyToInsert.add(company);
                currentUser.createFromCNPJResponse(procura, txtPassword.getText().toString());
                currentUser.setCompanyId(company.getId());
                currentUser.setId(db.fetchLastId("users") + 1);
                userToInsert.add(currentUser);

                List<String> adressQuerys = new ArrayList<>();
                adressQuerys = enderecoDAO.insertQuerys(adressToInsert);
                List<String> companyQuerys = new ArrayList<>();
                companyQuerys = companyDAO.insertQuerys(companyToInsert);
                List<String> userQuerys = new ArrayList<>();
                userQuerys = userDAO.insertQuerys(userToInsert);

                try {
                    db.runNonSelectQuery(adressQuerys);
                    db.runNonSelectQuery(companyQuerys);
                    db.runNonSelectQuery(userQuerys);
                }catch (Exception e){
                    Log.d("ERROR " + e.getClass(), e.getMessage());
                }

                UserSessionManager userToInsert = new UserSessionManager(SignUp.this);
                userToInsert.saveUser(currentUser);

                Intent intent = new Intent(SignUp.this, Main.class);
                startActivity(intent);
                finish();
            }
        });


//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spDocumentType.setAdapter(adapter);
//
//        spDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedPosition = spDocumentType.getSelectedItem().toString();
//                switch (selectedPosition){
//                    case "CNPJ":
//                        btnFetchDocument.setText(R.string.document_CNPJ);
//                        break;
//                    case "CPF":
//                        btnFetchDocument.setText(R.string.document_CPF);
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
}