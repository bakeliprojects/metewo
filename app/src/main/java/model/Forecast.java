package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lamine Dieng on 19/06/2017.
 */

public class Forecast {
    @SerializedName("forecastday")
    @Expose
    private List<ForecastDay> forcastDays;


    public List<ForecastDay> getForcastDays() {
        return forcastDays;
    }

    public void setForcastDays(List<ForecastDay> forcastDays) {
        this.forcastDays = forcastDays;
    }


}
