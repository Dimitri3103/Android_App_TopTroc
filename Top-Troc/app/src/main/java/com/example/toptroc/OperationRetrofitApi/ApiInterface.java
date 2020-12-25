package com.example.toptroc.OperationRetrofitApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //Inscription
    @GET("email_registration.php")
    Call<Users> performEmailRegistration(
            @Query("user_name") String user_name,
            @Query("user_email") String user_email,
            @Query("user_phone") String user_phone,
            @Query("user_password") String user_password
    );

    //Connexion
    @GET("email_login.php")
    Call<Users> performEmailLongin(
            @Query("user_email") String user_email,
            @Query("user_password") String user_password
    );
}
