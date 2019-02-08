package com.example.historical.controller;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Slot;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.SlotRepository;

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
@RequestMapping(value = WebConfig.SLOT)
public class SlotController extends CrudController<Slot, SlotRepository> {

    public SlotController() {
    }

    @Autowired
    public SlotController(SlotRepository slotRepository) {
        super(slotRepository);
        logger = LoggerFactory.getLogger(SlotController.class);
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
    ResponseEntity<?> create(@RequestBody Slot slot) {
        logger.debug("create() with body {}", slot);
        if (slot == null || slot.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Slot returnedEntity = repository.save(slot);
        return ResponseEntity.ok(returnedEntity);
    }
}
