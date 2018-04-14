package com.example.jamesli.codewarschallenge.repository;

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
}