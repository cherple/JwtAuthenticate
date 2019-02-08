package com.example.historical.util;

import com.example.historical.enumeration.ReportingType;
import com.example.historical.model.Account;
import com.example.historical.model.Permit;
import com.example.historical.model.Dateslot;
import com.example.historical.model.Timeslot;
import com.example.historical.model.Poc;
import com.example.historical.model.Coordinate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Test entity generation functions for unit tests.
 * 
 * @author JX
 *
 */
public class TestEntities {

    public static final Long ID1 = 1L;
    public static final Long ID2 = 2L;
    public static final Long ID3 = 3L;
    public static final Long ID4 = 4L;
    
//    public static Permit getPermit(Long id, Long liveId) {
//        return new Permit(id, liveId, ReportingType.MOBILE_DYNAMIC, 1.2, new Date(), new Date(), new Date(), new ArrayList<>(), new Account(),1);
//    }
//
//    public static Permit getPermitWithAccount(Long id, Long liveId, Long accountId, Long accountLiveId) {
//        return new Permit(id, liveId, ReportingType.MOBILE_DYNAMIC, 1.2, new Date(), new Date(), new Date(), new ArrayList<>(), new Account(accountId, accountLiveId),2);
//    }
    
    public static Permit getPermit(Long id, Long liveId) {
        List<Dateslot> dateslots = new ArrayList<Dateslot>();
        for (int x = 0; x < 2; ++x) {
            Dateslot dateslot = new Dateslot();
            List<Timeslot> timeslots = new ArrayList<Timeslot>();
            for (int y = 0; y < 3; ++y) {
                timeslots.add(new Timeslot());
            }
            dateslot.setTimeslots(timeslots);
            dateslots.add(dateslot);
        }
        List<Coordinate> ao = new ArrayList<Coordinate>();
        ao.add(new Coordinate(null, 8L, 1, 1.1, 1.2));
        ao.add(new Coordinate(null, 9L, 2, 1.3, 1.4));
        List<Poc> pocs = new ArrayList<Poc>();
        pocs.add(new Poc(null, 4L, "John", "Mohawk Company", 12345678));
        pocs.add(new Poc(null, 5L, "Peter", "Ninja Company", 88889999));
        return new Permit(id, liveId, "Zone1", dateslots, "Location1", "TRAINING", "DESC1", ao, "MODEL1", "c205", "c209", "KCQ1", "REF1", "REM1", pocs, new Account());
    }
    public static Permit getPermitWithAccount(Long id, Long liveId, Long accountId, Long accountLiveId) {
        List<Dateslot> dateslots = new ArrayList<Dateslot>();
        for (int x = 0; x < 2; ++x) {
            Dateslot dateslot = new Dateslot();
            List<Timeslot> timeslots = new ArrayList<Timeslot>();
            for (int y = 0; y < 3; ++y) {
                timeslots.add(new Timeslot());
            }
            dateslot.setTimeslots(timeslots);
            dateslots.add(dateslot);
        }
        List<Coordinate> ao = new ArrayList<Coordinate>();
        ao.add(new Coordinate(null, 5L, 1, 1.1, 1.2));
        ao.add(new Coordinate(null, 6L, 2, 1.3, 1.4));
        List<Poc> pocs = new ArrayList<Poc>();
        pocs.add(new Poc(null, 6L, "John", "Mohawk Company", 12345678));
        pocs.add(new Poc(null, 7L, "Peter", "Ninja Company", 88889999));
        return new Permit(id, liveId, "Zone2", dateslots, "Location2", "TRAINING", "DESC2", ao, "MODEL2", "c205", "c209", "KCQ2", "REF2", "REM2", pocs, new Account(accountId, accountLiveId));
    }
}
