package com.example.jamesli.codewarschallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AuthoredChallenge implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("rank")
    private String rank;

    public AuthoredChallenge(String id, String name, String description, String rank) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rank = rank;
    }

    public AuthoredChallenge(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.description = source.readString();
        this.rank = source.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(rank);
    }

    public static final Creator<AuthoredChallenge> CREATOR = new Creator<AuthoredChallenge>() {

        @Override
        public AuthoredChallenge[] newArray(int size) {
            return new AuthoredChallenge[size];
        }

        @Override
        public AuthoredChallenge createFromParcel(Parcel source) {
            return new AuthoredChallenge(source);
        }
    };
}
