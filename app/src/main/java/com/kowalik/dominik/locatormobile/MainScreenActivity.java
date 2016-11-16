package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kowalik.dominik.logic.Helpers;
import com.kowalik.dominik.logic.LocationInfoProvider;
import com.kowalik.dominik.model.FriendsName;
import com.kowalik.dominik.model.LocationInfo;
import com.kowalik.dominik.model.User;
import com.kowalik.dominik.web.EndpointInterface;
import com.kowalik.dominik.web.ServiceGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenActivity extends AppCompatActivity {

    Context context;
    LocationInfoProvider locationInfoProvider;
    FloatingActionButton sendStatusFAB;
    EditText statusEditText;
    TextView ownStatusTextView;
    List<User> users;
    Helpers<User> helpers = new Helpers<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (Objects.equals(id, R.id.locationSender)) {
            updateCoordinates(locationInfoProvider.getLocationInfo());
            return true;
        } else if (Objects.equals(id, R.id.refresh)) {
            getFriends();
        }

        Intent intent = new Intent(context, MapsActivity.class);
        helpers.saveList("friends", context, users);
        startActivity(intent);

        return true;
    }


    public void getFriends() {
        new ServiceGenerator(context);
        String username = Helpers.getStringFromPreference("username", context);

        EndpointInterface loginService =
                ServiceGenerator.createService(EndpointInterface.class, username, Helpers.getStringFromPreference("password", context));

        Call<List<User>> call = loginService.users(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Helpers.showText("aktualizcja mapy", context);
                users = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Helpers.showText("coś poszło nie tak, sprawdź internet", context);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        locationInfoProvider = new LocationInfoProvider();
        ownStatusTextView = (TextView) findViewById(R.id.ownStatusTextView);
        statusEditText = (EditText) findViewById(R.id.statusEditText);
        sendStatusFAB = (FloatingActionButton) findViewById(R.id.sendStatusFab);
        context = this;

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                locationInfoProvider = new LocationInfoProvider(context);
            }
        }, 1000);

        ownStatusTextView.setText(Helpers.getStringFromPreference("status", context));
        sendStatusFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(statusEditText.getText().toString());
            }
        });


        getFriends();
    }

    public void updateStatus(final String statement) {
        new ServiceGenerator(context);
        String username = Helpers.getStringFromPreference("username", context);

        EndpointInterface loginService =
                ServiceGenerator.createService(EndpointInterface.class, username, Helpers.getStringFromPreference("password", context));

        Call<Void> call = loginService.updateStatus(username, statement);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Helpers.showText("status zaktualizowany", context);
                ownStatusTextView.setText(statement);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Helpers.showText("coś poszło nie tak, sprawdź internet", context);
            }
        });
    }

    public void updateCoordinates(LocationInfo locationInfo) {
        new ServiceGenerator(context);

        String username = Helpers.getStringFromPreference("username", context);

        EndpointInterface loginService =
                ServiceGenerator.createService(EndpointInterface.class, username, Helpers.getStringFromPreference("password", context));

        Call<Void> call = loginService.updateCoordinates(username, locationInfo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Helpers.showText("lokalizacja zaktualizowana", context);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Helpers.showText("coś poszło nie tak, sprawdź gps i internet", context);
            }
        });
    }


}
