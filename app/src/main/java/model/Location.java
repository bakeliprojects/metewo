package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lamine Dieng on 19/06/2017.
 */

public class Location {
    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("localtime")
    @Expose
    private String localTime;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }
}
