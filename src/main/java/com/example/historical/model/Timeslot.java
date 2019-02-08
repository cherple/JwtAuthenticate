/*
 * Created by Chris
 * Timeunit can be used to convert time
 */
package com.example.historical.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Timeslot extends Identifiable{
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateslot_id", referencedColumnName="id")
    private Dateslot dateslot;
    
    private long startTime;
    
    private long endTime;
    
    private double altitude;
    
    public Timeslot(){
        this(null, 2L, 1548232477956L,1548232504617L,0);
    }
    
    public Timeslot(Long id, Long liveId, long startTime, long endTime, double altitude) {
        super(null, liveId);
        this.startTime = startTime;
        this.endTime = endTime;
        this.altitude = altitude;
    }
    
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    
    @JsonIgnore
    public Dateslot getDateslot() {
        return dateslot;
    }
    
    public void setDateslot(Dateslot dateslot) {
        this.dateslot = dateslot;
    }
}