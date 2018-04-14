
package com.example.jamesli.codewarschallenge.model;

import com.google.gson.annotations.SerializedName;

public class Overall {

    @SerializedName("rank")
    private String Rank;

    @SerializedName("score")
    private String Score;

    public String getRank() {
        return Rank;
    }

    public String getScore() {
        return Score;
    }
}
