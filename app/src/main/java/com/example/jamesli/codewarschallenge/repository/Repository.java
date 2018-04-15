package com.example.jamesli.codewarschallenge.repository;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.model.User;

import io.reactivex.Observable;

public interface Repository {

    Observable<User> getUser(String username);

    Observable<CompletedChallengeResponse> getCompletedChallenges(String username);

    Observable<AuthoredChallengeResponse> getAuthoredChallenges(String username);

    Observable<CompletedChallengeResponse> getCompletedChallengesByPage(String username, int page);
}