package com.example.jamesli.codewarschallenge.network;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CodewarApiService {

    @GET("api/v1/users/{username}")
    Observable<User> getUser(@Path("username") String username);

    @GET("api/v1/users/{username}/code-challenges/completed")
    Observable<CompletedChallengeResponse> getCompletedChallenges(@Path("username") String username);

    @GET("api/v1/users/{username}/code-challenges/authored")
    Observable<AuthoredChallengeResponse> getAuthoredChallenges(@Path("username") String username);

    @GET("api/v1/users/{username}/code-challenges/completed")
    Observable<CompletedChallengeResponse> getCompletedChallengesByPage(@Path("username") String username, @Query("page") int page);
}
