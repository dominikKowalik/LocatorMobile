package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kowalik.dominik.logic.Helpers;
import com.kowalik.dominik.model.Account;
import com.kowalik.dominik.web.EndpointInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity implements LocationListener {

    Button registerB;
    EditText passwordET, usernameET, emailET;
    Boolean inputOK = false;
    Context context;
    private Location location;

    /**
     * aplication's starting point
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = this;

        registerB = (Button) findViewById(R.id.registerButton);

        passwordET = (EditText) findViewById(R.id.passwordEditText);
        usernameET = (EditText) findViewById(R.id.userNameEditText);
        emailET = (EditText) findViewById(R.id.emailEditText);

        //  onLocationChanged(getLocation());

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.suggestToTurnOnGpsIfOff(context);
                Account account = new Account();
                    account.setUsername(usernameET.getText().toString());
                    account.setEmail(emailET.getText().toString());
                    account.setPassword(passwordET.getText().toString());

                    register(account);
            }
        });
/**
 * Sending request and getting response using retrofit 2 library after clicking buttonGetData
 */
    }
    /**
     * Checking if gps is on. If gps is off its going to start new gps properties activity.
     */


    public void register(Account account) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("Actuall account", account.toString());

        EndpointInterface endpointInterface = retrofit.create(EndpointInterface.class);
        Call<Void> call = endpointInterface.register(account);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){

                if(Objects.equals(response.code(), 409)){
                    Helpers.showText("Ta Nazwa Użytkownika Jest Zajęta", context);
                    return;
                }
                Helpers.showText("Zostałeś Zarejestrowany", context);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Helpers.showText("Nie Zostałeś Zarejestrowany" + t.toString()   , context);
            }
        });
    }

    public Location getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        final Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            onLocationChanged(location);
        } else {
            Helpers.showText("Lokalizacja nie dostępna\n", context);
        }
        return location;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }



//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }


    /**
     * returning true if text in text view is null
     *
     * @param editText
     * @return
     */

//    public boolean equalEditTextContentWithNull(EditText editText) {
//        if (editText.getText().toString().length() < 2) {
//            Helpers.showText("Dane Wyglądają Na Nieprawidłowe", this);
//        }
//        return false;
//    }

//
//    @Override
//    public void afterTextChanged(Editable s) {
//        if(equalEditTextContentWithNull(emailET)){
//            inputOK = false;
//        }
//    }
}
