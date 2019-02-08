package com.example.historical.integration;

import com.example.historical.config.WebConfig;
import com.example.historical.model.Account;
import com.example.historical.model.Authority;
import com.example.historical.model.AuthorityName;
import com.example.historical.model.Permit;
import com.example.historical.model.Coordinate;
import com.example.historical.repository.AccountRepository;
import com.example.historical.repository.AuthorityRepository;
import com.example.historical.repository.PermitRepository;
import com.example.historical.repository.CoordinateRepository;
import com.example.historical.util.JsonUtil;
import com.example.historical.util.TestEntities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PermitIntegrationTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PermitRepository permitRepository;
    
    @Autowired
    CoordinateRepository coordinateRepository;

    String url;
    public void setUrl(String url) {
        this.url = url;
    }

    @Test
    public void testCreatePermitsWithDuplicateAccount() throws Exception {
        Permit permit1 = TestEntities.getPermitWithAccount(null, TestEntities.ID1, null, TestEntities.ID3);
        Permit permit2 = TestEntities.getPermitWithAccount(null, TestEntities.ID2, null, TestEntities.ID3);
        
        Authority auth1 = new Authority(AuthorityName.ROLE_USER);
        Authority auth2 = new Authority(AuthorityName.ROLE_ADMIN);
        
        mockMvc.perform(
                post("/authority/").contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(auth1)))
                .andExpect(status().isOk()).andReturn();
        mockMvc.perform(
                post("/authority/").contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(auth2)))
                .andExpect(status().isOk()).andReturn();

        // Test creation
        mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(permit1)))
                .andExpect(status().isOk()).andReturn();
        mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(permit2)))
                .andExpect(status().isOk()).andReturn();

        Account account = this.accountRepository.findByLiveId(TestEntities.ID3);
        Assert.assertNotNull(account.getPermits());
    }

    /**
     * Setup before unit tests are run.
     */
    @Before
    public void before() {
        this.setUrl(WebConfig.PERMIT + "/");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
