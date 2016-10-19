package com.kowalik.dominik.locatormobile;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kowalik.dominik.model.LocationInfo;
import com.kowalik.dominik.model.LocationInfoImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {

    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        responseTextView = (TextView) findViewById(R.id.response);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.1.15:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndpointInterface endpointInterface = retrofit.create(EndpointInterface.class);
        Call<LocationInfoImpl> call = endpointInterface.getUser();
        call.enqueue(new Callback<LocationInfoImpl>() {
            @Override
            public void onResponse(Call<LocationInfoImpl> call, Response<LocationInfoImpl> response) {
                LocationInfo locationInfo = response.body();
                responseTextView.setText(locationInfo.getLatitude());
            }

            @Override
            public void onFailure(Call<LocationInfoImpl> call, Throwable t) {
                responseTextView.setText("Coś Poszło nie tak");
            }
        });


        setContentView(R.layout.activity_main);
    }

    public String getStringResource(int id) {
        return getResources().getString(id);
    }



}
