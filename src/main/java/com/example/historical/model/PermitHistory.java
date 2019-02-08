package com.example.historical.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

import com.example.historical.enumeration.Action;
import com.example.historical.model.Permit;

/**
 * @author Naresh Joshi
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PermitHistory {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "permit_id", foreignKey = @ForeignKey(name = "FK_permit_history_permit"))
    private Permit permit;

    @CreatedBy
    private String modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private Action action;

    public PermitHistory() {
    }

    public PermitHistory(Permit permit, Action action) {
        this.permit = permit;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return new Date(modifiedDate.getTime());
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = new Date(modifiedDate.getTime());
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}