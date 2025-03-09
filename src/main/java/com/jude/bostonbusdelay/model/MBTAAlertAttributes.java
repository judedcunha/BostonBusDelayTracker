package com.jude.bostonbusdelay.model;
public class MBTAAlertAttributes {
    private String header;
    private String description;
    private String effect; // Add this field

    // Getters and setters
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}