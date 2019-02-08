package com.example.historical.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Coordinate extends Identifiable{
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id", referencedColumnName="id")
    private Permit permit;
    
    private int seqNum;
    
    private double lat;
    
    private double lon;
    
    public Coordinate() {
        this(null, null, 0,0,0);
    }
    
    public Coordinate(Long id, Long liveId, int seqNum, double lat, double lon) {
        super(id, liveId);
        this.seqNum = seqNum;
        this.lat = lat;
        this.lon = lon;
    }
    
    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
    public void setPermit(Permit permit) {
        this.permit = permit;
    }
    
    @JsonIgnore
    public Permit getPermit() {
        return permit;
    }
    
}