package com.example.jamesli.codewarschallenge.repository;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.model.User;
import com.example.jamesli.codewarschallenge.network.CodewarApiService;

import io.reactivex.Observable;

public class ApiRepository implements Repository {

    private CodewarApiService mCodewarApiService;

    public ApiRepository(CodewarApiService codewarApiService) {
        mCodewarApiService = codewarApiService;
    }

    @Override
    public Observable<User> getUser(String username) {
        return mCodewarApiService.getUser(username);
    }

    @Override
    public Observable<CompletedChallengeResponse> getCompletedChallenges(String username) {
        return mCodewarApiService.getCompletedChallenges(username);
    }

    @Override
    public Observable<AuthoredChallengeResponse> getAuthoredChallenges(String username) {
        return mCodewarApiService.getAuthoredChallenges(username);
    }
}