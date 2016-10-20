package com.kowalik.dominik.locatormobile;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    TextView responTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responTextView = (TextView) findViewById(R.id.response);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.134:50001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



            EndpointInterface endpointInterface = retrofit.create(EndpointInterface.class);
            Call<LocationInfoImpl> call = endpointInterface.getUser();

            call.enqueue(new Callback<LocationInfoImpl>() {

                @Override
                public void onResponse(Call<LocationInfoImpl> call, Response<LocationInfoImpl> response) {
                    responTextView.setText(response.body().getLatitude());

                }

                @Override
                public void onFailure(Call<LocationInfoImpl> call, Throwable t) {

                }
            });

    }

}
