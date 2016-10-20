package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kowalik.dominik.model.LocationInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView responTextView;
    Location location;
    Button buttonGetData;
    LocationManager locationManager;
    String provider;
    boolean enabled;

    /**
     * aplication's starting point
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responTextView = (TextView) findViewById(R.id.response);
        buttonGetData = (Button) findViewById(R.id.button);

        suggestToTurnOnGpsIfOff();


        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        final Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            onLocationChanged(location);
        } else {
            responTextView.setText("Location not available");
        }
/**
 * Sending request and getting response using retrofit 2 library after clicking buttonGetData
 */

        buttonGetData.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.url))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LocationInfo locationInfo = new LocationInfo(location);

                EndpointInterface endpointInterface = retrofit.create(EndpointInterface.class);

                Call<LocationInfo> call1 = endpointInterface.getLocation();
                Call<LocationInfo> call = endpointInterface.setLocation(locationInfo);

                call.enqueue(new Callback<LocationInfo>() {
                    @Override
                    public void onResponse(Call<LocationInfo> call, Response<LocationInfo> response) {
                      //  responTextView.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<LocationInfo> call, Throwable t) {
                    }
                });
            }
        });
    }

    /**
     * Checking if gps is on. If gps is off its going to start new gps properties activity.
     */
    private void suggestToTurnOnGpsIfOff() {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        responTextView.setText(enabled + "");

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    /**
     *
     */


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
}
