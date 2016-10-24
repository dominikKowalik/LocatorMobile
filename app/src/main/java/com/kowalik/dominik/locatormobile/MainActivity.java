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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kowalik.dominik.model.LocationInfo;
import com.kowalik.dominik.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView responTextView;
    Button getDataButton;

    Location location;

    /**
     * aplication's starting point
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responTextView = (TextView) findViewById(R.id.response);
        getDataButton = (Button) findViewById(R.id.button);

        suggestToTurnOnGpsIfOff();
        onLocationChanged(getLocation());
/**
 * Sending request and getting response using retrofit 2 library after clicking buttonGetData
 */
        getDataButton.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendLocation(location);
        }});
    }

    /**
     * Checking if gps is on. If gps is off its going to start new gps properties activity.
     */
    private void suggestToTurnOnGpsIfOff() {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        responTextView.setText(enabled + "");

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void sendLocation(Location location){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationInfo locationInfo = new LocationInfo(location);
        Log.d("Actuall locationInfo",locationInfo.toString());

        EndpointInterface endpointInterface = retrofit.create(EndpointInterface.class);
        Call<Void> call = endpointInterface.setLocation(new User("1","1","1", locationInfo,null));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                //responTextView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call< Void>  call, Throwable t){
            }
        });
    }

    public Location getLocation(){
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
            responTextView.setText("Location not available");
        }

        return location;
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
}
