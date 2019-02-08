package com.example.historical.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.historical.model.Identifiable;
import com.example.historical.repository.CrudRepository;
import com.example.historical.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for the the abstract <code>CrudController</code>.
 * 
 * @author JX
 *
 * @param <T>
 *            Entity type.
 * @param <R>
 *            Repository type.
 */
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public abstract class CrudControllerTest<T extends Identifiable, R extends CrudRepository<T>> {

    protected R repository;
    protected String url;

    protected T entityNew;
    protected T entityExisting;

    @Autowired
    protected MockMvc mockMvc;
    
    Logger logger = LoggerFactory.getLogger(CrudControllerTest.class);

    /**
     * Sets the link to the repository.
     * 
     * @param repository
     *            Linked repository for the controller under test.
     */
    public void init(R repository) {
        this.repository = repository;
    }

    /**
     * Set the URL modifier for the test cases to use.
     * 
     * @param url
     *            URL modifier for making requests.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the non-abstract entities for the test cases to use.
     * 
     * @param entityNew
     *            New entity which has not been assigned an Id.
     * @param entityExisting
     *            Existing entity which is in the database.
     */
    public void setEntities(T entityNew, T entityExisting) {
        this.entityNew = entityNew;
        this.entityExisting = entityExisting;
    }

    @Test
    public void testCreate() throws Exception {

        // Setup mock return values
        Mockito.when(repository.save(entityNew)).thenReturn(entityExisting);

        // Test successful creation
        mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(entityNew)))
                .andExpect(status().isOk()).andDo(document("." + url + "create")).andReturn();
    }

    @Test
    public void testCreateFail() throws Exception {

        // Test failed creation
        mockMvc.perform(
                post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(entityExisting)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void testGet() throws Exception {

        // Setup mock return values
        Long idExisting = entityExisting.getId();
        Mockito.when(repository.findById(idExisting)).thenReturn(Optional.of(entityExisting));

        // Test successful retrieval
        MvcResult result = mockMvc.perform(get(url + idExisting)).andExpect(status().isOk())
                .andDo(document("." + url + "get")).andReturn();

        // Check that returned object has the correct id
        String resultStr = result.getResponse().getContentAsString();
        JSONObject resultJson = new JSONObject(resultStr);
        assertTrue(resultJson.get("id").toString().equals(idExisting.toString()));
    }

    @Test
    public void testGetFail() throws Exception {

        // Setup mock return values
        Long idNew = entityNew.getId();
        Mockito.when(repository.findById(idNew)).thenReturn(Optional.ofNullable(null));

        // Test failed retrieval due to incident not existing
        mockMvc.perform(get(url + idNew)).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAll() throws Exception {

        List<T> entities = new ArrayList<T>();
        entities.add(entityExisting);
        Mockito.when(repository.findAll()).thenReturn(entities);

        // Test successful retrieval
        MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(document("." + url + "getAll"))
                .andReturn();
        String resultStr = result.getResponse().getContentAsString();
        JSONArray array = new JSONArray(resultStr);
        JSONObject obj = (JSONObject) array.get(0);
        assertTrue(obj.get("id").toString().equals(entityExisting.getId().toString()));
    }

    @Test
    public void testUpdate() throws Exception {

        // Setup mock return values
        Mockito.when(repository.findById(entityExisting.getId())).thenReturn(Optional.of(entityExisting));
        Mockito.when(repository.save(entityExisting)).thenReturn(entityExisting);

        // Test successful update
        mockMvc.perform(
                put(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(entityExisting)))
                .andExpect(status().isOk()).andDo(document("." + url + "update")).andReturn();

    }

    @Test
    public void testUpdateFail() throws Exception {

        // Setup mock return values
        Mockito.when(repository.findById(entityExisting.getId())).thenReturn(Optional.ofNullable(null));

        // Test failed update due to non-existing incident
        mockMvc.perform(
                put(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtil.toJsonString(entityExisting)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(url + entityExisting.getId())).andExpect(status().isOk())
                .andDo(document("." + url + "delete")).andReturn();
    }

    @Test
    public void testDeleteAll() throws Exception {
        mockMvc.perform(delete(url)).andExpect(status().isOk()).andDo(document("." + url + "deleteAll")).andReturn();
    }

}
