package com.safetynet.alert.service;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.impl.FireStationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FireStationServiceTest {

    @InjectMocks
    private FireStationServiceImpl fireStationService;

    @Mock
    private Data data;

    @BeforeEach
    public void setUp(){
        FireStation fireStation = new FireStation(1, "test");
        List<FireStation> fireStations= new ArrayList<>();
        fireStations.add(fireStation);
        when(data.getFirestations()).thenReturn(fireStations);
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
        boolean result = fireStationService.updateFireStation(new FireStation(3, "test"));

        //Then
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void deleteFireStationTest(){
        // Given

        // When
        boolean result = fireStationService.removeFireStation(new FireStation(1, "test"));

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
        boolean result = fireStationService.addFireStation(new FireStation(1, "test"));

        assertThat(result).isEqualTo(false);
    }

}
