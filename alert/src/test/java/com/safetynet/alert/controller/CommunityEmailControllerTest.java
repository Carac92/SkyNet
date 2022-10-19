package com.safetynet.alert.controller;

import com.safetynet.alert.dto.CommunityEmailDTO;
import com.safetynet.alert.service.CommunityEmailService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CommunityEmailController.class)
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommunityEmailService communityEmailService;

    @Test
    public void testGetCommunityEmail() throws Exception {
        //GIVEN
        CommunityEmailDTO dto = new CommunityEmailDTO();
        dto.setEmail("testGetFireDTO@example.com");
        List<CommunityEmailDTO> dtoList = new ArrayList<CommunityEmailDTO>();
        dtoList.add(dto);

        //WHEN
        when(communityEmailService.getAllEmailOfCity(ArgumentMatchers.anyString())).thenReturn(dtoList);

        // THEN
        mvc.perform(get("/communityEmail")
                .param("city", "Culver")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetNoCommunityEmail() throws Exception {
        // GIVEN
        List<CommunityEmailDTO> dtoList = new ArrayList<>();

        //WHEN
        when(communityEmailService.getAllEmailOfCity(ArgumentMatchers.anyString())).thenReturn(dtoList);

        //THEN
        mvc.perform(get("/communityEmail")
                .param("city", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCommunityEmailWithOutCity() throws Exception {
        //GIVEN
        CommunityEmailDTO dto = new CommunityEmailDTO();
        dto.setEmail("testGetFireDTO@example.com");
        List<CommunityEmailDTO> dtoList = new ArrayList<CommunityEmailDTO>();
        dtoList.add(dto);

        //WHEN
        when(communityEmailService.getAllEmailOfCity(ArgumentMatchers.anyString())).thenReturn(dtoList);

        //THEN
        mvc.perform(get("/communityEmail")
                        .param("city", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
