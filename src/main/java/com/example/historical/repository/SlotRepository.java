package com.example.historical.repository;

import com.example.historical.model.Slot;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface SlotRepository extends CrudRepository<Slot> {
    
    Slot findByLiveId(Long liveId);

}