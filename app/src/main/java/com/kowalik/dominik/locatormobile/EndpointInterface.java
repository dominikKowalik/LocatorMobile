package com.kowalik.dominik.locatormobile;

import com.kowalik.dominik.model.LocationInfo;
import com.kowalik.dominik.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dominik on 2016-10-19.
 */

public interface EndpointInterface{


    @GET("/locator/user")
    Call<User> getLocation();

    @POST("/locator/user")
    Call<Void> setLocation(@Body User user);
}
