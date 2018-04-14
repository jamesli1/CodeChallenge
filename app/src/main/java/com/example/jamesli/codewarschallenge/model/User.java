package com.example.jamesli.codewarschallenge.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    private String username;
    @SerializedName("name")
    private String name;
    @SerializedName("ranks")
    private Ranks ranks;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Ranks getRanks() {
        return ranks;
    }
}
