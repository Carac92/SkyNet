package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.service.FireService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FireController.class)
public class FireControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FireService fireService;

    @Test
    public void testGetFireDTO() throws Exception {
        // GIVEN
        FireDTO fireDTO = new FireDTO();
        fireDTO.setLastName("Test");
        fireDTO.setFirstName("Test");
        fireDTO.setPhoneNumber("123456");
        fireDTO.setAge(20);
        fireDTO.setMedications(new ArrayList<>());
        fireDTO.setAllergies(new ArrayList<>());
        fireDTO.setStationNumber(3);
        List<FireDTO> fireDTOs = new ArrayList<>();
        fireDTOs.add(fireDTO);

        // WHEN
        when(fireService.getAllPeopleByFireStationAddress(ArgumentMatchers.anyString())).thenReturn(fireDTOs);

        // THEN
        mvc.perform(get("/fire")
                .param("address", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNoPersonByFireDTO() throws Exception {
        List<FireDTO> fireDTOs = new ArrayList<>();

        //WHEN
        when(fireService.getAllPeopleByFireStationAddress(ArgumentMatchers.anyString())).thenReturn(fireDTOs);

        //THEN
        mvc.perform(get("/fire")
                .param("address", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetFireDTOWithNoAdress() throws Exception {
        // GIVEN
        FireDTO fireDTO = new FireDTO();
        fireDTO.setLastName("Test");
        fireDTO.setFirstName("Test");
        fireDTO.setPhoneNumber("123456");
        fireDTO.setAge(20);
        fireDTO.setMedications(new ArrayList<>());
        fireDTO.setAllergies(new ArrayList<>());
        fireDTO.setStationNumber(3);
        List<FireDTO> fireDTOs = new ArrayList<>();
        fireDTOs.add(fireDTO);

        //WHEN
        when(fireService.getAllPeopleByFireStationAddress(ArgumentMatchers.anyString())).thenReturn(fireDTOs);

        //THEN
        mvc.perform(get("/fire")
                        .param("address", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
