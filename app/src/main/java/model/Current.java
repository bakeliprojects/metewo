package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lamine Dieng on 19/06/2017.
 */

public class Current {
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    @SerializedName("condition")
    @Expose
    private Condition condition;

    @SerializedName("wind_mph")
    @Expose
    private Double windMph;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Double getTempC() {
        return tempC;
    }

    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }
}
