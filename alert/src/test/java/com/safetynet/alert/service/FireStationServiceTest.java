package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.impl.FireStationServiceImpl;
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
 * Test of the Service FireStationServiceImpl
 * verify that the service add, update, and delete correctly the data on the Bean Data.
 * verify that the service doesn't return an empty List on the get method.
 */
@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @InjectMocks
    private FireStationServiceImpl fireStationService;

    @Mock
    private Data data;

    @BeforeEach
    public void setUp(){
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
    public void addFireStationTest(){
        // Given

        //When
        boolean result = fireStationService.addFireStation(new FireStation(2, "testtest"));
        //Then
        assertThat(result).isEqualTo(true);

    }

    @Test
    public void updateFireStationTest(){
        // Given

        //When
        boolean result = fireStationService.updateFireStation(new FireStation(1, "1509 Culver St"));

        //Then
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void deleteFireStationTest(){
        // Given

        // When
        boolean result = fireStationService.removeFireStation(new FireStation(1, "1509 Culver St"));

        // Then
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void deleteFireStationThatDoesNotExistTest(){
        boolean result = fireStationService.removeFireStation(new FireStation());

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void updateFireStationThatDoesNotExist(){
        boolean result = fireStationService.updateFireStation(new FireStation());

        assertThat(result).isEqualTo(false);
    }
    @Test
    public void addFireStationThatAlreadyExistsTest(){
        boolean result = fireStationService.addFireStation(new FireStation(1, "1509 Culver St"));

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void getFireStationDTO() throws Exception {
        List<FireStationDTO> result = fireStationService.getAllPeopleInTheFireStation(1);
        FireStationFinalDTO finalResult = fireStationService.GetAllPeopleCoveredByFireStation(result);

        assertThat(finalResult).isNotNull();
        assertThat(finalResult.getFireStationDTOS()).isEqualTo(result);
    }

}
