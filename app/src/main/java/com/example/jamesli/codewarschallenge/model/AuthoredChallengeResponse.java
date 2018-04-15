package com.example.jamesli.codewarschallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AuthoredChallengeResponse {

    @SerializedName("data")
    private List<AuthoredChallenge> authoredChallenge;

    public List<AuthoredChallenge> getAuthoredChallenge() {
        return authoredChallenge == null ? new ArrayList<>(0) : authoredChallenge;
    }
}
