package com.example.historical.controller;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Poc;
import com.example.historical.model.Dateslot;
import com.example.historical.model.Timeslot;
import com.example.historical.model.Coordinate;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.PermitRepository;
import com.example.historical.repository.PocRepository;
import com.example.historical.repository.DateslotRepository;
import com.example.historical.repository.TimeslotRepository;
import com.example.historical.repository.CoordinateRepository;

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
@RequestMapping(value = WebConfig.PERMIT)
public class PermitController extends CrudController<Permit, PermitRepository> {

    public PermitController() {
    }

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PocRepository pocRepository;
    
    @Autowired
    DateslotRepository dateslotRepository;
    
    @Autowired
    TimeslotRepository timeslotRepository;
    
    @Autowired
    CoordinateRepository coordinateRepository;

    @Autowired
    public PermitController(PermitRepository permitRepository) {
        super(permitRepository);
        logger = LoggerFactory.getLogger(PermitController.class);
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
    ResponseEntity<?> create(@RequestBody Permit permit) {

        logger.debug("create() with body {}", permit);
        if (permit == null || permit.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
      Account permitAccount = permit.getAccount();
      if (permitAccount != null) {
          Account existingAccounts = accountRepository.findByLiveId(permitAccount.getLiveId());
          if (existingAccounts != null) {
              permit.setAccount(existingAccounts);
          } else {
              Account newAccount = accountRepository.save(permitAccount);
              permit.setAccount(newAccount);
          }
      }
        
        /*
         * Assuming new permit posting always generates new coordinates/pocs/slots (not retrieved from current entry in DB)
         */
        
        List<Poc> pocs = permit.getPocs();
        if (pocs != null) {
            for (int x = 0; x < pocs.size(); ++x) {
                Poc poc = pocs.get(x);
                poc.setPermit(permit);
            }
        }
        
        List<Coordinate> coordinates = permit.getCoordinates();
        if (coordinates != null) {
            for (int x = 0; x < coordinates.size(); ++x) {
                Coordinate coordinate = coordinates.get(x);
                coordinate.setPermit(permit);
            }
        }
        
        List<Dateslot> dateslots = permit.getDateslots();
        if (dateslots != null) {
            for (int x = 0; x < dateslots.size(); ++x) {
                List<Timeslot> timeslots = dateslots.get(x).getTimeslots();
                for (int y = 0; y < timeslots.size(); ++y) {
                    Timeslot timeslot = timeslots.get(y);
                    timeslot.setDateslot( dateslots.get(x) );
                }
                Dateslot dateslot = dateslots.get(x);
                dateslot.setPermit(permit);
            }
        }

        // TODO: Save the drone, permiter etc entity

        Permit returnedEntity = repository.save(permit);
        repository.flush();
        return ResponseEntity.ok(returnedEntity);
    }
    
    @RequestMapping(value = "/get")
    @ResponseBody
    public Permit getFoosBySimplePath(
            @RequestParam("id") long id) {
        return repository.findById(id).get();
    }
    
    @RequestMapping(value = "/delete")
    public void deletePermit(
            @RequestParam("id") long id) {
        repository.deleteById(id);
    }
    
    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResponseEntity<?> returnPermitToEdit(
            @RequestParam("id") long id) {
        Optional<Permit> permitop = repository.findById(id);
        if (!permitop.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Permit permit = permitop.get();
        
        //TODO : Possible race condition, change bad request to OK
        
        //Check if permit editable before returning
        boolean editable = permit.getEditable();
        if (editable == false) {
            return new ResponseEntity<>("Entry currently being edited by someone else...", HttpStatus.BAD_REQUEST);
        } 

        //Update permit's editable flag
        permit.setEditable(false);
        
        Permit returnedEntity = repository.save(permit);
        repository.flush();
        return ResponseEntity.ok(returnedEntity);
    }
    
    @PutMapping("")
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Permit permit) {

        logger.debug("update() with body {}", permit);
        if (permit == null || permit.getId() == null || !repository.findById(permit.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        //After successfully updating a permit, reset editable flag to true
        permit.setEditable(true);

        Permit returnedEntity = repository.save(permit);
        repository.flush();
        return ResponseEntity.ok(returnedEntity);
    }

}
