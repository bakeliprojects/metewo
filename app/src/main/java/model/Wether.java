package model;

/**
 * Created by Lamine Dieng on 17/06/2017.
 */

public class Wether {
    private String locationRegion;
    private String currentTempC;
    private String conditionIcon;
    private String conditionText;
    private String windMph;
    private String minTemp;
    private String maxTemp;
    private String currentLastUpdated;

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getCurrentTempC() {
        return currentTempC;
    }

    public void setCurrentTempC(String currentTempC) {
        this.currentTempC = currentTempC;
    }

    public String getConditionIcon() {
        return conditionIcon;
    }

    public void setConditionIcon(String conditionIcon) {
        this.conditionIcon = conditionIcon;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public String getWindMph() {
        return windMph;
    }

    public void setWindMph(String windMph) {
        this.windMph = windMph;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getCurrentLastUpdated() {
        return currentLastUpdated;
    }

    public void setCurrentLastUpdated(String currentLastUpdated) {
        this.currentLastUpdated = currentLastUpdated;
    }
}
