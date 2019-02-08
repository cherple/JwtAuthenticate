package com.example.historical.repository;

import com.example.historical.model.Dateslot;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface DateslotRepository extends CrudRepository<Dateslot> {
    
    Dateslot findByLiveId(Long liveId);

}