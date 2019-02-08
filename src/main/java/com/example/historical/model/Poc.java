package com.example.historical.model;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Represents a historical drone permit.
 * 
 * @author JX
 * 
 */
@Entity
public class Poc extends Identifiable {

    private String name;
    
    private String company;
    
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_id", referencedColumnName="id")
    private Permit permit;

    public Poc() {
        this(null, null, null, null, 0);
    }

    public Poc(Long id, 
            Long liveId,
            String name,
            String company,
            int number) {
        super(id, liveId);
        this.name = name;
        this.company = company;
        this.number = number;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }
    
//    @JsonIgnore
//    public Permit getPermit() {
//        return permit;
//    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

}
