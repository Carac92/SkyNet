package com.safetynet.alert.service;

import com.safetynet.alert.dto.CommunityEmailDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.impl.CommunityEmailServiceImpl;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Quentin_Caracatzanis
 * Test of the Service CommunityEmailServiceImpl.
 * verify that the service doesn't return an empty List.
 */
@ExtendWith(SpringExtension.class)
public class CommunityEmailServiceTest {
    @InjectMocks
    CommunityEmailServiceImpl communityEmailService;

    @Mock
    Data data;

    @BeforeEach
    public void setUp() throws Exception {
        List<String> medications = Arrays.asList("aznol:350mg","hydrapermazol:100mg");
        List<String> allergies = Arrays.asList("peanut");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", new Date(01/01/2010), medications, allergies);
        List<MedicalRecord> medicalRecords= new ArrayList<>();
        medicalRecords.add(medicalRecord);
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512","jaboyd@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        FireStation fireStation = new FireStation(1, "1509 Culver St");
        List<FireStation> fireStations= new ArrayList<>();
        fireStations.add(fireStation);

        when(data.getFirestations()).thenReturn(fireStations);
        when(data.getMedicalrecords()).thenReturn(medicalRecords);
        when(data.getPersons()).thenReturn(persons);
    }

    @Test
    public void testGetCommunityEmailDto() throws Exception {

        List<CommunityEmailDTO> result = communityEmailService.getAllEmailOfCity("Culver");

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }
}
