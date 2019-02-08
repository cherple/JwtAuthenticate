package com.example.historical.controller;

import com.example.historical.model.Identifiable;
import com.example.historical.repository.CrudRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Abstract class for a <code>CrudController</code> with mappings to a
 * <code>CrudRepository</code>.
 * 
 * @author JX
 *
 * @param <T>
 *            Entity type.
 * @param <R>
 *            Repository type.
 */
public abstract class CrudController<T extends Identifiable, R extends CrudRepository<T>> {

    protected Logger logger = LoggerFactory.getLogger(CrudController.class);

    protected R repository;

    public CrudController() {
    }

    /**
     * Full constructor for a CrudController.
     * 
     * @param repository
     *            Repository that the controller binds to.
     */
    public CrudController(R repository) {
        setRepository(repository);
    }

    /**
     * Sets the repository for the controller.
     * 
     * @param repository
     *            Repository that the controller binds to.
     */
    public void setRepository(R repository) {
        this.repository = repository;
    }

    /**
     * Creates a new entity.
     *
     * @param entity
     *            Request body containing the entity to be created.
     * @return Created entity.
     */
    @PostMapping("")
    public @ResponseBody ResponseEntity<?> create(@RequestBody T entity) {
        logger.debug("create() with body {}", entity);
        if (entity == null || entity.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        T returnedEntity = repository.save(entity);
        return ResponseEntity.ok(returnedEntity);
    }

    /**
     * Retrieves an existing entity.
     *
     * @param id
     *            Id of the entity to retrieve.
     * @return Retrieved entity.
     */
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> get(@PathVariable Long id) {
        logger.debug("get() with id {}", id);
        Optional<T> returnedEntity = repository.findById(id);
        return ResponseEntity.ok(returnedEntity.orElseThrow(IllegalArgumentException::new));
    }

    /**
     * Retrieves all existing entities.
     *
     * @return List of existing entities.
     */
    @GetMapping("")
    public @ResponseBody ResponseEntity<?> getAll() {
        logger.debug("getAll()");
        Iterable<T> entities = repository.findAll();
        return ResponseEntity.ok(entities);
    }

    /**
     * Updates an existing entity.
     *
     * @param entity
     *            Contains the entity with updated parameters.
     * @return Updated entity.
     */
    @PutMapping("")
    public @ResponseBody ResponseEntity<?> update(@RequestBody T entity) {
        logger.debug("update() with body {}", entity);
        if (entity == null || entity.getId() == null || !repository.findById(entity.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        T returnedEntity = repository.save(entity);
        return ResponseEntity.ok(returnedEntity);
    }

    /**
     * Deletes an existing entity.
     *
     * @param id
     *            Id of the entity to delete.
     * @return Success if deleted.
     */
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Long id) {
        logger.debug("delete() with id {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok("");
    }

    /**
     * Deletes all existing entities.
     * 
     * @return Success if deleted.
     */
    @DeleteMapping("")
    public @ResponseBody ResponseEntity<?> deleteAll() {
        logger.debug("deleteAll()");
        repository.deleteAll();
        return ResponseEntity.ok("");
    }

}