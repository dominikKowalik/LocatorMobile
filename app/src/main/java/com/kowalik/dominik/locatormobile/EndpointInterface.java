package com.kowalik.dominik.locatormobile;

import com.kowalik.dominik.model.LocationInfo;
import com.kowalik.dominik.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by dominik on 2016-10-19.
 */

public interface EndpointInterface{


    @GET("/locator/user")
    Call<List<User>> getAllUsers();

    @POST("/locator/user")
    Call<Void> insertUser(@Body User user);

    @GET("/locator/user/{id}")
    Call<User> getUser(@Path("id") long id);

    @GET("/locator/user/{name}/{lastName}/{emailAdress}")
    Call<User> getUser(@Path("name") String name, @Path("lastName") String lastName, @Path("emailAdress") String emailAdress);

    @DELETE("/locator/user")
    Call<Void> deleteAllUsers();

    @DELETE("/locator/user/{id}")
    Call<Void> deleteUser(@Path("id") long id);
}
