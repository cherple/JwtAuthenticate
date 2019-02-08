package com.example.historical.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.geo.Point;

import com.example.historical.config.PermitEntityListener;

/**
 * Represents a drone permit.
 * 
 * @author JX
 * 
 */
@Entity
@EntityListeners(PermitEntityListener.class)
public class Permit extends Identifiable {

    private String zone;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "permit")
    private List<Dateslot> dateslots;

    private String location;

    private String activityType;

    private String activityDesc;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "permit")
    private List<Coordinate> coordinates;

    private String droneModel;

    private String infoParty;

    private String actionParty;

    private String kcq;

    private String refNum;

    private String remarks;

    @ManyToOne
    private Account account;
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "permit")
    private List<Poc> pocs;
    
    private boolean editable;

    public Permit() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Permit(Long id, 
            Long liveId,
            String zone,
            List<Dateslot> dateslots,
            String location,
            String activityType,
            String activityDesc,
            List<Coordinate> coordinates,
            String droneModel,
            String infoParty,
            String actionParty,
            String kcq,
            String refNum,
            String remarks,
            List<Poc> pocs,
            Account account) {
        super(id, liveId);
        this.zone = zone;
        this.location = location;
        this.dateslots = dateslots;
        this.activityType = activityType;
        this.activityDesc = activityDesc;
        this.coordinates = coordinates;
        this.droneModel = droneModel;
        this.infoParty = infoParty;
        this.actionParty = actionParty;
        this.kcq = kcq;
        this.refNum = refNum;
        this.remarks = remarks;
        this.pocs = pocs;
        this.account = account;
        this.editable = true;
    }

    public Permit(Long id, 
            Long liveId,
            String zone,
            List<Dateslot> dateslots,
            String location,
            String activityType,
            String activityDesc,
            List<Coordinate> coordinates,
            String droneModel,
            String infoParty,
            String actionParty,
            String kcq,
            String refNum,
            String remarks,
            List<Poc> pocs) {
        this(id, liveId, zone, dateslots, location, activityType, activityDesc, coordinates, droneModel, infoParty, actionParty, kcq, refNum, remarks, pocs, new Account());
    }


    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }

    public List<Dateslot> getDateslots() {
        return dateslots;
    }
    
    public void setDateslots(List<Dateslot> dateslots) {
        this.dateslots = dateslots;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getString() {
        return activityType;
    }
    
    public void setActivtyType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityDesc() {
        return activityDesc;
    }
    
    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public List<Coordinate> getCoordinates(){
        return coordinates;
    }
    
    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public String getDroneModel() {
        return droneModel;
    }
    
    public void setDroneModel(String droneModel) {
        this.droneModel = droneModel;
    }

    public String getInfoParty() {
        return infoParty;
    }
    
    public void setInfoParty(String infoParty) {
        this.infoParty = infoParty;
    }

    public String getActionParty() {
        return actionParty;
    }
    
    public void setActionParty(String actionParty) {
        this.actionParty = actionParty;
    }

    public String getKcq() {
        return kcq;
    }
    
    public void setKcq(String kcq) {
        this.kcq = kcq;
    }

    public String getRefNum() {
        return refNum;
    }
    
    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public List<Poc> getPocs(){
        return pocs;
    }
    
    public void setPocs(List<Poc> pocs) {
        this.pocs = pocs;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public boolean getEditable() {
        return editable;
    }
}
