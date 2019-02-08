/*
 * Created by Chris
 * Timeunit can be used to convert time
 */
package com.example.historical.model;

import java.util.Date;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
public class Slot extends Identifiable{
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id", referencedColumnName="id")
    private Permit permit;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private long startTime;
    
    private long endTime;
    
    private double altitude;
    
    public Slot(){
        this(null, 2L, new Date(),1548232477956L,1548232504617L,0);
    }
    
    public Slot(Long id, Long liveId, Date date, long startTime, long endTime, double altitude) {
        super(id, liveId);
        this.date = new Date(date.getTime());
        this.startTime = startTime;
        this.endTime = endTime;
        this.altitude = altitude;
    }
    
    public Date getDate() {
        return new Date(date.getTime());
    }
    
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
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
    
//    public Permit getPermit() {
//        return permit;
//    }
    
    public void setPermit(Permit permit) {
        this.permit = permit;
    }
}