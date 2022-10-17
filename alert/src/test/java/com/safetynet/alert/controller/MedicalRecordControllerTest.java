package com.safetynet.alert.controller;

import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.MedicalRecordService;
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
@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    public void testAddMedicalRecord() throws Exception {
        when(medicalRecordService.addMedicalRecord(ArgumentMatchers.any(MedicalRecord.class)))
                .thenReturn(true);

        mvc.perform(post("/medicalRecord")
                .content("{ \"firstName\":\"Test\", \"lastName\":\"Test\", \"birthdate\":\"01/01/1994\", \"medications\":[], \"allergies\":[\"peanut\"] }")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        when(medicalRecordService.updateMedicalRecord(ArgumentMatchers.any(MedicalRecord.class)))
                .thenReturn(true);

        mvc.perform(put("/medicalRecord")
                .content("{ \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2020\", \"medications\":[\"Doliprane\"], \"allergies\":[\"peanut\"] }")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        when(medicalRecordService.removeMedicalRecord(ArgumentMatchers.any(MedicalRecord.class)))
                .thenReturn(true);

        mvc.perform(delete("/medicalRecord")
                .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
