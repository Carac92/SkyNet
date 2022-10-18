package com.safetynet.alert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        when(fireStationService.updateFireStation(ArgumentMatchers.any(FireStation.class))).thenReturn(new FireStation());

        mvc.perform(put("/firestation")
                .content("{ \"station\":9, \"address\": \"1509 Culver St\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStation() throws Exception {
        when(fireStationService.removeFireStation(ArgumentMatchers.any(FireStation.class)))
                .thenReturn(true);

        mvc.perform(delete("/firestation")
                .content("{ \"station\": 3, \"address\": \"1509 Culver St\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
