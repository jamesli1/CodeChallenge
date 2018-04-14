package com.example.jamesli.codewarschallenge.network;

import com.example.jamesli.codewarschallenge.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CodewarApiService {

    @GET("api/v1/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
