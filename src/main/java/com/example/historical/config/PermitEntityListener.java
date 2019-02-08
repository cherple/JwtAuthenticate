package com.example.historical.config;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.example.historical.model.Permit;
import com.example.historical.model.PermitHistory;
import com.example.historical.enumeration.Action;
import com.example.historical.websoc.PermitUpdates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static com.example.historical.enumeration.Action.*;

/**
 * @author JX
 */

@Component
public class PermitEntityListener {
    
    @Autowired
    private PermitUpdates permitUpdates;

    @PrePersist
    public void prePersist(Permit target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Permit target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Permit target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    private void perform(Permit target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new PermitHistory(target, action));
        //Send permitHistory to client via websocket to update changes

//        permitUpdates
//        .sendUpdatedPermit(
//                new PermitHistory(
//                        target, 
//                        action));
    }

}