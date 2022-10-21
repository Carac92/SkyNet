package com.safetynet.alert.controller;

import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Quentin_Caracatzanis
 * Test of the controller PersonInfoController.
 * Verify that the return status is correct with or without parameters
 * Verify that the return status is correct with an empty List
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonInfoService personInfoService;

    @Test
    public void testGetPersonInfo() throws Exception {
        // GIVEN
        PersonInfoDTO dto = new PersonInfoDTO();
        dto.setAddress("test");
        dto.setEmail("test@example.com");
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAge(24);
        dto.setAllergies(new ArrayList<>());
        dto.setMedications(new ArrayList<>());
        List<PersonInfoDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        // WHEN
        when(personInfoService.getPersonInfoByName(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(dtos);

        //THEN
        mvc.perform(get("/personInfo")
                .param("firstName", "test")
                .param("lastName", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonInfoEmpty() throws Exception {
        //GIVEN
        List<PersonInfoDTO> dtos = new ArrayList<>();

        //WHEN
        when(personInfoService.getPersonInfoByName(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(dtos);

        //THEN
        mvc.perform(get("/personInfo")
                        .param("firstName", "test")
                        .param("lastName", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPersonWithNameEmpty() throws Exception {
        // GIVEN
        PersonInfoDTO dto = new PersonInfoDTO();
        dto.setAddress("test");
        dto.setEmail("test@example.com");
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setAge(24);
        dto.setAllergies(new ArrayList<>());
        dto.setMedications(new ArrayList<>());
        List<PersonInfoDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        // WHEN
        when(personInfoService.getPersonInfoByName(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(dtos);

        //THEN
        mvc.perform(get("/personInfo")
                        .param("firstName", "")
                        .param("lastName", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
