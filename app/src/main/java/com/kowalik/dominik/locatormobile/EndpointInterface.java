package com.kowalik.dominik.locatormobile;

import com.kowalik.dominik.model.Account;
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


    @GET("user")
    Call<List<User>> getAllUsers();

    @POST("register/{username}/{password}/{email}")
    Call<Void> register(@Path("username") String username, @Path("password")
            String password, @Path("email") String email);

    @POST("register")
    Call<Void> register(@Body Account account);

    @POST("user")
    Call<Void> insertUser(@Body User user);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") long id);

    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);

    @DELETE("user")
    Call<Void> deleteAllUsers();
}
