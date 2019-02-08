package com.example.historical.repository;

import com.example.historical.model.Timeslot;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface TimeslotRepository extends CrudRepository<Timeslot> {
    
    Timeslot findByLiveId(Long liveId);

}