package com.example.jamesli.codewarschallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CompletedChallengeResponse {

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("totalItems")
    private int totalItems;

    @SerializedName("data")
    private List<CompletedChallenge> completedChallenges;

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<CompletedChallenge> getCompletedChallenges() {
        return completedChallenges == null ? new ArrayList<>(0) : completedChallenges;
    }
}
