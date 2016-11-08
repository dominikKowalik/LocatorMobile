package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.model.User;
import com.kowalik.dominik.web.ServiceGenerator;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText passwordTextView, loginTextView;
    Button loginButton;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordTextView = (EditText) findViewById(R.id.passwordEditText);
        loginTextView = (EditText) findViewById(R.id.usernameEditText);
        loginButton = (Button) findViewById(R.id.loginButton);

        context = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Helpers.isGpsOn(context))
                    login(loginTextView.getText().toString(), passwordTextView.getText().toString());
                else
                    Helpers.suggestToTurnOnGpsIfOff(context);

            }
        });
    }

    public void login(final String username, final String password) {
        new ServiceGenerator(context);

        EndpointInterface loginService =
                ServiceGenerator.createService(EndpointInterface.class, username, password);

        Call<User> call = loginService.login(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Helpers.showText("Zalogowano" + response.body().toString(), context);

                    SharedPreferences settings = getSharedPreferences("com.kowalik.dominik", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();

                    Intent mainScreenActivity = new Intent(context, MainScreenActivity.class);
                    startActivity(mainScreenActivity);
                } else {
                    Helpers.showText("Użytkownik nie istnieje", context);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // something went completely south (like no internet CONNECTION)
            }
        });
    }

}
