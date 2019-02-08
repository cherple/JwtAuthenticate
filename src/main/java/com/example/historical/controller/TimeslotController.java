package com.example.historical.controller;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Timeslot;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.TimeslotRepository;

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
@RequestMapping(value = WebConfig.TIMESLOT)
public class TimeslotController extends CrudController<Timeslot, TimeslotRepository> {

    public TimeslotController() {
    }

    @Autowired
    public TimeslotController(TimeslotRepository timeslotRepository) {
        super(timeslotRepository);
        logger = LoggerFactory.getLogger(TimeslotController.class);
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
    ResponseEntity<?> create(@RequestBody Timeslot timeslot) {
        logger.debug("create() with body {}", timeslot);
        if (timeslot == null || timeslot.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Timeslot returnedEntity = repository.save(timeslot);
        return ResponseEntity.ok(returnedEntity);
    }
}
