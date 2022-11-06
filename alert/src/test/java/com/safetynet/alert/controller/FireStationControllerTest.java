package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.FireStationService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Quentin_Caracatzanis
 * Test of the controller FireStationController
 * Verify that the return status is correct with or without parameters
 * Verify that the return status is correct with an empty List
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FireStationService fireStationService;

    @Test
    public void testAddFireStation() throws Exception {
        when(fireStationService.addFireStation(ArgumentMatchers.any(FireStation.class))).thenReturn(true);

        mvc.perform(post("/firestation")
                        .content("{ \"station\":3, \"address\": \"boyd\"}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateFireStation() throws Exception {
        when(fireStationService.updateFireStation(anyString(), ArgumentMatchers.any(FireStation.class)))
                .thenReturn(true);

        mvc.perform(put("/firestation")
                .param("address", "1509 Culver St")
                .content("{ \"station\":9, \"address\": \"1509 Culver St\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStation() throws Exception {
        when(fireStationService.removeFireStation(anyInt(),anyString()))
                .thenReturn(true);

        mvc.perform(delete("/firestation")
                .param("station", "3")
                .param("address", "1509 Culver St")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteFireStationThatIsEmpty() throws Exception {
        when(fireStationService.removeFireStation(anyInt(),anyString()))
                .thenReturn(true);
        mvc.perform(delete("/firestation")
                .param("station", "")
                .param("address", "")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testUpdateFireStationThatIsEmpty()throws Exception {
        when(fireStationService.updateFireStation(anyString(),ArgumentMatchers.any(FireStation.class)))
                .thenReturn(true);
        mvc.perform(put("/firestation")
                .param("address", "")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddFireStationThatIsEmpty()throws Exception {
        when(fireStationService.addFireStation(ArgumentMatchers.any(FireStation.class)))
                .thenReturn(true);

        mvc.perform(put("/firestation")
                .content("{}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetFireStationDTO()throws Exception {
        //GIVEN
        FireStationDTO dto = new FireStationDTO();
        dto.setMinor(true);
        dto.setPhoneNumber("tesst");
        dto.setAddress("test");
        dto.setLastName("test");
        dto.setFirstName("test");
        List<FireStationDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        FireStationFinalDTO dtoFinal = new FireStationFinalDTO();
        dtoFinal.setNumberOfAdults(0);
        dtoFinal.setNumberOfMinor(1);
        dtoFinal.setFireStationDTOS(dtos);

        //WHEN
        when(fireStationService.getAllPeopleInTheFireStation(ArgumentMatchers.anyInt())).thenReturn(dtos);
        when(fireStationService.GetAllPeopleCoveredByFireStation(dtos)).thenReturn(dtoFinal);

        //THEN
        mvc.perform(get("/firestation")
                .param("stationNumber", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFireStationDTOEmpty() throws Exception {
        //GIVEN
        List<FireStationDTO> dtos = new ArrayList<>();
        //WHEN
        when(fireStationService.getAllPeopleInTheFireStation(ArgumentMatchers.anyInt())).thenReturn(dtos);

        //THEN
        mvc.perform(get("/firestation")
                        .param("stationNumber", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void testGetFireStationDTOWithEmptyStationNumber() throws Exception {
        //GIVEN
        FireStationDTO dto = new FireStationDTO();
        dto.setMinor(true);
        dto.setPhoneNumber("tesst");
        dto.setAddress("test");
        dto.setLastName("test");
        dto.setFirstName("test");
        List<FireStationDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        FireStationFinalDTO dtoFinal = new FireStationFinalDTO();
        dtoFinal.setNumberOfAdults(0);
        dtoFinal.setNumberOfMinor(1);
        dtoFinal.setFireStationDTOS(dtos);

        //WHEN
        when(fireStationService.GetAllPeopleCoveredByFireStation(ArgumentMatchers.anyList())).thenReturn(dtoFinal);

        //THEN
        mvc.perform(get("/firestation")
                        .param("stationNumber", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
