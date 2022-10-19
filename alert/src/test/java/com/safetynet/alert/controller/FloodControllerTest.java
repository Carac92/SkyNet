package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FloodDTO;
import com.safetynet.alert.service.FloodService;
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
@WebMvcTest(controllers = FloodController.class)
public class FloodControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FloodService floodService;

    @Test
    public void testGetFloodDto()throws Exception {
        // GIVEN
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setMedications(new ArrayList<>());
        floodDTO.setAllergies(new ArrayList<>());
        floodDTO.setPhoneNumber("test");
        floodDTO.setAge(24);
        floodDTO.setAddress("test");
        floodDTO.setFirstName("test");
        floodDTO.setLastName("test");
        floodDTO.setStationNumber(3);
        List<FloodDTO> floodDTOs = new ArrayList<>();
        floodDTOs.add(floodDTO);

        //WHEN
        when(floodService.getAllHousesByAListOfStationNumber(ArgumentMatchers.anyList())).thenReturn(floodDTOs);

        //THEN
        mvc.perform(get("/flood/stations")
                .param("stations", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFloodDtoEmpty()throws Exception{
        // GIVEN
        List<FloodDTO> floodDTOs = new ArrayList<>();

        //WHEN
        when(floodService.getAllHousesByAListOfStationNumber(ArgumentMatchers.anyList())).thenReturn(floodDTOs);

        //THEN
        mvc.perform(get("/flood/stations")
                .param("stations", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetFloodDTOWithNoStationNumber()throws Exception{
        // GIVEN
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setMedications(new ArrayList<>());
        floodDTO.setAllergies(new ArrayList<>());
        floodDTO.setPhoneNumber("test");
        floodDTO.setAge(24);
        floodDTO.setAddress("test");
        floodDTO.setFirstName("test");
        floodDTO.setLastName("test");
        floodDTO.setStationNumber(3);
        List<FloodDTO> floodDTOs = new ArrayList<>();
        floodDTOs.add(floodDTO);

        //WHEN
        when(floodService.getAllHousesByAListOfStationNumber(ArgumentMatchers.anyList())).thenReturn(floodDTOs);

        //THEN
        mvc.perform(get("/flood/stations")
                        .param("stations", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
