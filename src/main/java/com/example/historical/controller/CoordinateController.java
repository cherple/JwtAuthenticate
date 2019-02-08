package com.example.historical.controller;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Permit;
import com.example.historical.model.Coordinate;
import com.example.historical.repository.CoordinateRepository;

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
@RequestMapping(value = WebConfig.COORDINATE)
public class CoordinateController extends CrudController<Coordinate, CoordinateRepository> {

    public CoordinateController() {
    }
    
    @Autowired
    CoordinateRepository coordinateRepository;

    @Autowired
    public CoordinateController(CoordinateRepository coordinateRepository) {
        super(coordinateRepository);
        logger = LoggerFactory.getLogger(CoordinateController.class);
    }

    /**
     * Creates a new entity.
     *
     * @param coordinate
     *            Request body containing the entity to be created.
     * @return Created entity.
     */
    @Override
    @PostMapping("")
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody Coordinate coordinate) {
        logger.debug("create() with body {}", coordinate);
        if (coordinate == null || coordinate.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Coordinate returnedEntity = repository.save(coordinate);
        return ResponseEntity.ok(returnedEntity);
    }
}
