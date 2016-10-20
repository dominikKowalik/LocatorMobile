package com.kowalik.dominik.locatormobile;

import com.kowalik.dominik.model.LocationInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dominik on 2016-10-19.
 */

public interface EndpointInterface{
    @GET("/Locator/getLocation")
    Call<LocationInfo> getLocation();

    @POST("/Locator/setLocation")
    Call<LocationInfo> setLocation(@Body LocationInfo locationInfo);
}
