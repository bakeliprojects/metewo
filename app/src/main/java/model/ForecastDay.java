package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Lamine Dieng on 19/06/2017.
 */

public class ForecastDay {
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("maxtemp_c")
    @Expose
    private String maxTempC;

    @SerializedName("mintemp_c")
    @Expose
    private String minTempC;

    @SerializedName("condition")
    @Expose
    private Condition condition;

    @SerializedName("day")
    @Expose
    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(String maxTempC) {
        this.maxTempC = maxTempC;
    }

    public String getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(String minTempC) {
        this.minTempC = minTempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
