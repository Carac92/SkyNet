package com.safetynet.alert.service;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.impl.FireStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @InjectMocks
    private FireStationServiceImpl fireStationService;

    @Mock
    private Data data;




    @Test
    public void addFireStationTest(){
        // Given
        FireStation fireStation = new FireStation(1, "test");
        //When
        boolean result = fireStationService.addFireStation(fireStation);

        //Then
        assertThat(result).isEqualTo(true);

    }

    @Test
    public void updateFireStationTest(){
        // Given
        FireStation fireStationToUpdate = new FireStation(3, "1509 Culver St");
        List <FireStation> fireStationList;
        //fireStationList = add

        //When
        FireStation result = fireStationService.updateFireStation(fireStationToUpdate);

        //Then
        assertThat(result).isNotNull();
    }

}
