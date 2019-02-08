package com.example.historical.websoc;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;

import com.example.historical.model.PermitHistory;

/**
 * @author JX
 */

@Component("permitUpdates")
public class PermitUpdates {
    
//    private static PermitUpdates instance;
    
    @Autowired
    private SimpMessagingTemplate template;
    
//    public static PermitUpdates getInstance() {
//        if (instance == null) {
//            instance = new PermitUpdates();
//        }
//        return instance;
//    }
    
    public void sendUpdatedPermit(PermitHistory permitHistory) {
        if (permitHistory != null) {
            this.template.convertAndSend("/changes", permitHistory);
        }
    }
    
}