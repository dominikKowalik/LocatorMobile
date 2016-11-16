package com.kowalik.dominik.web;

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

public interface EndpointInterface {
    @POST("register")
    Call<Void> register(@Body Account account);

    @GET("user/byname/{username}")
    Call<User> login(@Path("username") String username);

    @POST("user/updatecoordinates/{username}")
    Call<Void> updateCoordinates(@Path("username") String username,@Body LocationInfo locationInfo);

    @POST("user/updatestatus/{username}/{status}")
    Call<Void> updateStatus(@Path("username") String username, @Path("status") String status);

    @GET("friend/{username}")
    Call<List<User>> users(@Path("username") String username);
}