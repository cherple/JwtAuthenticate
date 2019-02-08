package com.example.historical.repository;

import com.example.historical.model.Poc;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface PocRepository extends CrudRepository<Poc> {
    
    Poc findByLiveId(Long liveId);
    
//    Poc findByNameAndCompany(String name, String company);

}