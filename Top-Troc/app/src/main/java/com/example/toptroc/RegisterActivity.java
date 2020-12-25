package com.example.toptroc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toptroc.OperationRetrofitApi.ApiClient;
import com.example.toptroc.OperationRetrofitApi.ApiInterface;
import com.example.toptroc.OperationRetrofitApi.Users;
import com.example.toptroc.Sessions.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailRegister,nameRegister,phoneRegister,passwordRegister;
    private Button buttonRegister;
    public static ApiInterface apiInterface;
    String user_id;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Cacher la barre de statut
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        init();

    }

    private void init() {
        emailRegister = (EditText) findViewById(R.id.emailRegister);
        nameRegister = (EditText) findViewById(R.id.nameRegister);
        phoneRegister = (EditText) findViewById(R.id.phoneRegister);
        passwordRegister = (EditText) findViewById(R.id.passwordRegister);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });
    }

    private void Registration() {
        String user_name = nameRegister.getText().toString().trim();
        String user_email = emailRegister.getText().toString().trim();
        String user_phone = phoneRegister.getText().toString().trim();
        String user_password = passwordRegister.getText().toString().trim();

        if (TextUtils.isEmpty(user_name))
        {
            nameRegister.setError("Nom obligatoire");
        }
        else if(TextUtils.isEmpty(user_email))
        {
            emailRegister.setError("Email obligatoire");
        }
        else if(TextUtils.isEmpty(user_phone))
        {
            phoneRegister.setError("Téléphone obligatoire");
        }
        else if(TextUtils.isEmpty(user_password))
        {
            passwordRegister.setError("Mot de passe obligatoire");
        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Inscription ...");
            dialog.setMessage("Patientez un instant pendant l'ajout de vos informations");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailRegistration(user_name,user_email,user_phone,user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")){
                        user_id = response.body().getUserId();
                        sessionManager.createSession(user_id);

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                         dialog.dismiss();
                    }else if (response.body().getResponse().equals("failed")){
                        Toast.makeText(RegisterActivity.this, "Erreur!! Essayer une nouvelle fois", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else if (response.body().getResponse().equals("already")){
                        Toast.makeText(RegisterActivity.this, "Cet email existe déjà!! Essayer avec un autre email ", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }
    }

    // Passage du formulaire d'inscription au formulaire de connexion //
    public void goToLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}