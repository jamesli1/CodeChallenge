package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.User;
import com.example.jamesli.codewarschallenge.repository.Repository;

import io.reactivex.Observable;

public class GetUserInteractor {

    private Repository mRepository;

    public GetUserInteractor(Repository repository) {
        mRepository = repository;
    }

    public Observable<User> getUserbyName(String username) {
        return mRepository.getUser(username);
    }
}