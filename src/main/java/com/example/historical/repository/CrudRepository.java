package com.example.historical.repository;

import com.example.historical.model.Identifiable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Abstract repository interface for all classes.
 * 
 * @author JX
 *
 */
@NoRepositoryBean
public abstract interface CrudRepository<T extends Identifiable> extends JpaRepository<T, Long> {

}
