package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.repository.Repository;

import io.reactivex.Observable;

public class GetCompletedChallengesByPageInteractor {

    private Repository mRepository;

    public GetCompletedChallengesByPageInteractor(Repository repository) {
        mRepository = repository;
    }

    public Observable<CompletedChallengeResponse> getCompletedChallengesByPage(String username, int page) {
        return mRepository.getCompletedChallengesByPage(username, page);
    }
}