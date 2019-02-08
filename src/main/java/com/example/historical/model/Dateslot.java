/*
 * Created by Chris
 * Timeunit can be used to convert time
 */
package com.example.historical.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Dateslot extends Identifiable{
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id", referencedColumnName="id")
    private Permit permit;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private String type;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "dateslot")
    private List<Timeslot> timeslots;
    
    public Dateslot(){
        this(null, 2L, new Date(), "SINGLE", new Date(), new Date(), new ArrayList<Timeslot>());
    }
    
    public Dateslot(Long id, Long liveId, Date date, String type, Date startDate, Date endDate, List<Timeslot> timeslots) {
        super(null, liveId);
        this.date = new Date(date.getTime());
        this.type = type;
        this.startDate = new Date(startDate.getTime());;
        this.endDate = new Date(endDate.getTime());;
        this.timeslots = timeslots;
    }
    
    public Date getDate() {
        return new Date(date.getTime());
    }
    
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Date getStartDate() {
        return new Date(startDate.getTime());
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = new Date(startDate.getTime());
    }
    
    public Date getEndDate() {
        return new Date(endDate.getTime());
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = new Date(endDate.getTime());
    }
    
    public List<Timeslot> getTimeslots(){
        return timeslots;
    }
    
    public void setTimeslots(List<Timeslot> timeslots) {
        this.timeslots = timeslots;
    }
    
    public void setPermit(Permit permit) {
        this.permit = permit;
    }
    
    @JsonIgnore
    public Permit getPermit() {
        return permit;
    }
}