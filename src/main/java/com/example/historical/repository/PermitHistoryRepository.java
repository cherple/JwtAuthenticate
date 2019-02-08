package com.example.historical.repository;

import com.example.historical.model.PermitHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for <code>Permit</code>.
 * 
 * @author JX
 */
public interface PermitHistoryRepository extends JpaRepository<PermitHistory, Integer> {

}