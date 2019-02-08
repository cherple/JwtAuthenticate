package com.example.historical.repository;

import com.example.historical.model.Coordinate;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface CoordinateRepository extends CrudRepository<Coordinate> {
    
    Coordinate findByLiveId(Long liveId);
    
    Coordinate findByLiveIdAndSeqNum(Long liveId, int seqNum);
    
    Coordinate findByPermit_IdAndSeqNum(Long Id, int seqNum);

}