package com.kowalik.dominik.locatormobile;

import com.kowalik.dominik.model.LocationInfoImpl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dominik on 2016-10-19.
 */

public interface EndpointInterface{
    @GET("getUser")
    Call<LocationInfoImpl> getUser();
}
