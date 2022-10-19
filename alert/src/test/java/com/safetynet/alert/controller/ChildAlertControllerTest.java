package com.safetynet.alert.controller;

import com.safetynet.alert.dto.ChildAlertDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.service.ChildAlertService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
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
@WebMvcTest(controllers = ChildAlertController.class)
public class ChildAlertControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ChildAlertService childAlertService;


    @Test
    public void getChildAlerts() throws Exception {
        //GIVEN
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setLastName("testGetFireDTO");
        childAlertDTO.setFirstName("testGetFireDTO");
        childAlertDTO.setAge(8);
        childAlertDTO.setAddress("testtest");
        List<ChildAlertDTO> childAlerts = new ArrayList<>();
        childAlerts.add(childAlertDTO);

        //WHEN

        when(childAlertService.findChildOfAnAddress(ArgumentMatchers.anyString())).thenReturn(childAlerts);

        //THEN
        mvc.perform(get("/childAlert")
                .param("address","testtest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void getNoChildWithAdress()throws Exception {
        // GIVEN
        List<ChildAlertDTO> childAlerts = new ArrayList<>();

        // WHEN
        when(childAlertService.findChildOfAnAddress(ArgumentMatchers.anyString())).thenReturn(childAlerts);

        //THEN
        mvc.perform(get("/childAlert")
                        .param("address","testGetFireDTO")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getChildWithOutAdress()throws Exception {
        //GIVEN
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setLastName("testGetFireDTO");
        childAlertDTO.setFirstName("testGetFireDTO");
        childAlertDTO.setAge(8);
        childAlertDTO.setAddress("testtest");
        List<ChildAlertDTO> childAlerts = new ArrayList<>();
        childAlerts.add(childAlertDTO);

        //WHEN

        when(childAlertService.findChildOfAnAddress(ArgumentMatchers.anyString())).thenReturn(childAlerts);

        //THEN
        mvc.perform(get("/childAlert")
                        .param("address","")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
