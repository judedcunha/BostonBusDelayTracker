package com.jude.bostonbusdelay.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MBTAAlertResponse {
    @JsonProperty("data")
    private List<MBTAAlert> data;

    // Add getter for the 'data' field
    public List<MBTAAlert> getData() {
        return data;
    }

    // Optionally, add a setter for the 'data' field
    public void setData(List<MBTAAlert> data) {
        this.data = data;
    }
}