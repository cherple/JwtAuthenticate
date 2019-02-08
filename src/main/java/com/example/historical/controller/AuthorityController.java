package com.example.historical.controller;

import java.util.Optional;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Authority;
import com.example.historical.repository.AuthorityRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for web service requests for <code>Permit</code>.
 * 
 * @author JX
 * 
 */
@RestController
@RequestMapping(value = WebConfig.AUTHORITY)
public class AuthorityController extends CrudController<Authority, AuthorityRepository> {

    public AuthorityController() {
    }

    @Autowired
    public AuthorityController(AuthorityRepository authorityRepository) {
        super(authorityRepository);
        logger = LoggerFactory.getLogger(AuthorityController.class);
    }

    /**
     * Creates a new entity.
     *
     * @param permit
     *            Request body containing the entity to be created.
     * @return Created entity.
     */
    @Override
    @PostMapping("")
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Authority authority) {
        logger.debug("create() with body {}", authority);
        if (authority == null || authority.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Authority returnedEntity = repository.save(authority);
        return ResponseEntity.ok(returnedEntity);
    }

}
