package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.repository.Repository;

import io.reactivex.Observable;

public class GetAuthoredChallengesInteractor {

    private Repository mRepository;

    public GetAuthoredChallengesInteractor(Repository repository) {
        mRepository = repository;
    }

    public Observable<AuthoredChallengeResponse> getAuthoredChallenges(String username) {
        return mRepository.getAuthoredChallenges(username);
    }

}
