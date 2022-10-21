package com.safetynet.alert.controller;

import com.safetynet.alert.dto.PhoneAlertDTO;
import com.safetynet.alert.service.PhoneAlertService;
import com.safetynet.alert.service.impl.PhoneAlertServiceImpl;
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
@WebMvcTest(controllers = PhoneAlertController.class)
public class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneAlertService phoneAlertService;

    @Test
    public void testGetPhoneAlert() throws Exception {
        //GIVEN
        PhoneAlertDTO dto = new PhoneAlertDTO();
        dto.setPhoneNumber("1234");
        List<PhoneAlertDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        //WHEN
        when(phoneAlertService.getAllPhoneForAFireStationNumber(ArgumentMatchers.anyInt())).thenReturn(dtos);

        //THEN
        mvc.perform(get("/phoneAlert")
                .param("firestation", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPhoneAlertEmpty() throws Exception {
        //GIVEN
        List<PhoneAlertDTO> dtos = new ArrayList<>();

        //WHEN
        when(phoneAlertService.getAllPhoneForAFireStationNumber(ArgumentMatchers.anyInt())).thenReturn(dtos);

        //THEN
        mvc.perform(get("/phoneAlert")
                        .param("firestation", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPhoneAlertWithFireStationEmpty() throws Exception {
        //GIVEN
        PhoneAlertDTO dto = new PhoneAlertDTO();
        dto.setPhoneNumber("1234");
        List<PhoneAlertDTO> dtos = new ArrayList<>();
        dtos.add(dto);

        //WHEN
        when(phoneAlertService.getAllPhoneForAFireStationNumber(ArgumentMatchers.anyInt())).thenReturn(dtos);

        //THEN
        mvc.perform(get("/phoneAlert")
                        .param("firestation", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
