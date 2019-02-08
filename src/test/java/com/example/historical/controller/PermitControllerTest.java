package com.example.historical.controller;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Authority;
import com.example.historical.model.Permit;
import com.example.historical.model.Poc;
import com.example.historical.model.Dateslot;
import com.example.historical.model.Timeslot;
import com.example.historical.model.Coordinate;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.AuthorityRepository;
import com.example.historical.repository.PermitRepository;
import com.example.historical.repository.PocRepository;
import com.example.historical.repository.DateslotRepository;
import com.example.historical.repository.TimeslotRepository;
import com.example.historical.repository.CoordinateRepository;
import com.example.historical.util.JsonUtil;
import com.example.historical.util.TestEntities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the <code>PermitController</code> class.
 * 
 * @author JX
 *
 */
@WebMvcTest(controllers = PermitController.class)
public class PermitControllerTest extends CrudControllerTest<Permit, PermitRepository> {

    @TestConfiguration
    static class Configuration {
        @Bean
        public PermitController permitController() {
            return new PermitController();
        }
    }

    @Autowired
    private PermitController permitController;

    @MockBean
    private PermitRepository permitRepository;

    @MockBean
    private AccountRepository accountRepository;
    
    @MockBean
    private PocRepository pocRepository;
    
    @MockBean
    private DateslotRepository dateslotRepository;
    
    @MockBean
    private TimeslotRepository timeslotRepository;
    
    @MockBean
    private CoordinateRepository coordinateRepository;
    
    @MockBean
    private AuthorityRepository authorityRepository;

    // TODO: test invalid fields

    /**
     * Setup before unit tests are run.
     */
    @Before
    public void before() {
        permitController.setRepository(permitRepository);
        super.init(permitRepository);
        super.setUrl(WebConfig.PERMIT + "/");
        super.setEntities(TestEntities.getPermit(null, null), TestEntities.getPermit(TestEntities.ID1, TestEntities.ID2));
    }
}
