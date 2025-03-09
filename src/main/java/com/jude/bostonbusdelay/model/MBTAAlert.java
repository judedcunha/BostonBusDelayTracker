package com.jude.bostonbusdelay.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MBTAAlert {
    private String id;

    @JsonProperty("attributes")
    private MBTAAlertAttributes attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MBTAAlertAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(MBTAAlertAttributes attributes) {
        this.attributes = attributes;
    }
}