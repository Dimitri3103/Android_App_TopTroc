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

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin,passwordLogin;
    private Button buttonLogin;
    public static ApiInterface apiInterface;
    String user_id;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Cacher la barre de statut
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);

        init();
    }

    private void init() {
        emailLogin = (EditText) findViewById(R.id.emailLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String user_email = emailLogin.getText().toString().trim();
        String user_password = passwordLogin.getText().toString().trim();

        if(TextUtils.isEmpty(user_email))
        {
            emailLogin.setError("Email obligatoire");
        }
        else if(TextUtils.isEmpty(user_password))
        {
            passwordLogin.setError("Mot de passe obligatoire");
        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Connexion ...");
            dialog.setMessage("Patientez un instant pendant la vérification de vos informations");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.performEmailLongin(user_email,user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")){
                        user_id = response.body().getUserId();
                        sessionManager.createSession(user_id);
                        sessionManager.setUserEmail(user_email);

                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();

                        dialog.dismiss();
                    }
                    else if (response.body().getResponse().equals("no_account")){
                        Toast.makeText(LoginActivity.this, "Aucun compte trouvé", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });

        }
    }

    // Passage du formulaire de connexion au formulaire d'inscription //
    public void goToRegister(View view) {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void backToMainPage(View view) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}