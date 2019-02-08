package com.example.historical.controller;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Dateslot;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.DateslotRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for web service requests for <code>Permit</code>.
 * 
 * @author JX
 * 
 */
@RestController
@RequestMapping(value = WebConfig.DATESLOT)
public class DateslotController extends CrudController<Dateslot, DateslotRepository> {

    public DateslotController() {
    }

    @Autowired
    public DateslotController(DateslotRepository dateslotRepository) {
        super(dateslotRepository);
        logger = LoggerFactory.getLogger(DateslotController.class);
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
    ResponseEntity<?> create(@RequestBody Dateslot dateslot) {
        logger.debug("create() with body {}", dateslot);
        if (dateslot == null || dateslot.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Dateslot returnedEntity = repository.save(dateslot);
        return ResponseEntity.ok(returnedEntity);
    }
}
