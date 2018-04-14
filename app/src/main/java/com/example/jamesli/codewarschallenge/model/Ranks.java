package com.example.jamesli.codewarschallenge.model;

import com.google.gson.annotations.SerializedName;

public class Ranks {

    @SerializedName("overall")
    private Overall Overall;

    public Overall getOverall() {
        return Overall;
    }

}
