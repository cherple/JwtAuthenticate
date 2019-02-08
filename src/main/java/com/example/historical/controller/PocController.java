package com.example.historical.controller;

import java.util.Optional;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Poc;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.PocRepository;

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
@RequestMapping(value = WebConfig.POC)
public class PocController extends CrudController<Poc, PocRepository> {

    public PocController() {
    }

    @Autowired
    public PocController(PocRepository pocRepository) {
        super(pocRepository);
        logger = LoggerFactory.getLogger(PocController.class);
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
    ResponseEntity<?> create(@RequestBody Poc poc) {
        logger.debug("create() with body {}", poc);
        if (poc == null || poc.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Poc returnedEntity = repository.save(poc);
        return ResponseEntity.ok(returnedEntity);
    }

}
