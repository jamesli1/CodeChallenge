package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.repository.Repository;

import io.reactivex.Observable;

public class GetCompletedChallengesInteractor {

    private Repository mRepository;

    public GetCompletedChallengesInteractor(Repository repository) {
        mRepository = repository;
    }

    public Observable<CompletedChallengeResponse> getCompletedChallenges(String username) {
        return mRepository.getCompletedChallenges(username);
    }
}