package com.example.jamesli.codewarschallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CompletedChallenge implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("slug")
    private String slug;
    @SerializedName("completedAt")
    private String completedAt;

    public CompletedChallenge(String id, String name, String slug, String completedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.completedAt = completedAt;
    }

    public CompletedChallenge(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.slug = source.readString();
        this.completedAt = source.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeString(completedAt);
    }

    public static final Creator<CompletedChallenge> CREATOR = new Creator<CompletedChallenge>() {

        @Override
        public CompletedChallenge[] newArray(int size) {
            return new CompletedChallenge[size];
        }

        @Override
        public CompletedChallenge createFromParcel(Parcel source) {
            return new CompletedChallenge(source);
        }
    };
}
