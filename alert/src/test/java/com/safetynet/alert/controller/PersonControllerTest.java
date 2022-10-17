package com.safetynet.alert.controller;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PersonService personService;

    @Test
    public void testAddPerson() throws Exception {
        when(personService.addPerson(ArgumentMatchers.any(Person.class)))
                .thenReturn(true);

        mvc.perform(post("/person")
                .content("{ \"firstName\":\"test\", \"lastName\":\"tester\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"test@email.com\" }")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    public void testUpdatePerson() throws Exception {
        when(personService.updatePerson(ArgumentMatchers.any(Person.class)))
                .thenReturn(true);

        mvc.perform(put("/person")
                .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        when(personService.deletePerson(ArgumentMatchers.any(Person.class)))
                .thenReturn(true);

        mvc.perform(delete("/person")
                .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\" }")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
