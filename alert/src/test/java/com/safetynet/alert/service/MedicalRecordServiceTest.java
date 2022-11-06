package com.safetynet.alert.service;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.impl.MedicalRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Quentin_Caracatzanis
 * Test of the Service MedicalRecordServiceImpl
 * verify that the service add, update, and delete correctly the data on the Bean Data.
 */
@ExtendWith(SpringExtension.class)
public class MedicalRecordServiceTest {

    @InjectMocks
    MedicalRecordServiceImpl medicalRecordService;

    @Mock
    Data data;

    @BeforeEach
    public void setUp(){
        List<String> medications = Arrays.asList("aznol:350mg","hydrapermazol:100mg");
        List<String> allergies = Arrays.asList("peanut");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", new Date(01/01/1990), medications, allergies);
        List<MedicalRecord> medicalRecords= new ArrayList<>();
        medicalRecords.add(medicalRecord);
        when(data.getMedicalrecords()).thenReturn(medicalRecords);
    }

    @Test
    public void addMedicalRecordTest(){
        // GIVEN
        List<String> medications = Arrays.asList("");
        List<String> allergies = Arrays.asList("peanut");
        MedicalRecord medicalRecord = new MedicalRecord("Test", "Test", new Date(01/01/1990), medications, allergies);
        //WHEN
        boolean result = medicalRecordService.addMedicalRecord(medicalRecord);

        // THEN
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void updateMedicalRecordTest(){
        // GIVEN
        List<String> medications = Arrays.asList("testGetFireDTO");
        List<String> allergies = Arrays.asList("testGetFireDTO");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", new Date(01/01/1980), medications, allergies);

        //WHEN
        boolean result = medicalRecordService.updateMedicalRecord("John", "Boyd", medicalRecord);

        //THEN
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void deleteMedicalRecordTest(){

        // WHEN
        boolean result = medicalRecordService.removeMedicalRecord("John", "Boyd");

        // THEN
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void deleteMedicalRecordThatDoesNotExistTest(){
        boolean result = medicalRecordService.removeMedicalRecord("","");

        assertThat(result).isEqualTo(false);
    }
    @Test
    public void updateMedicalRecordThatDoesNotExistTest(){
        boolean result = medicalRecordService.updateMedicalRecord("","",new MedicalRecord());

        assertThat(result).isEqualTo(false);
    }
    @Test
    public void addMedicalRecordThatAlreadyExistsTest(){
        boolean result = medicalRecordService.addMedicalRecord(new MedicalRecord("John", "Boyd", null, null, null));
        assertThat(result).isEqualTo(false);
    }
}
