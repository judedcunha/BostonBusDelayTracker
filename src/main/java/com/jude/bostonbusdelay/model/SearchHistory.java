package com.jude.bostonbusdelay.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String routeId;
    private LocalDateTime searchTime;
    private int delayCount;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRouteId() {
        return routeId;
    }
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    public LocalDateTime getSearchTime() {
        return searchTime;
    }
    public void setSearchTime(LocalDateTime searchTime) {
        this.searchTime = searchTime;
    }
    public int getDelayCount() {
        return delayCount;
    }
    public void setDelayCount(int delayCount) {
        this.delayCount = delayCount;
    }

}