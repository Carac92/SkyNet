package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.FireStation;

import java.io.IOException;
import java.util.List;

public interface FireStationService {

    boolean updateFireStation(FireStation fireStationToUpdate);
    boolean addFireStation(FireStation fireStation);
    boolean removeFireStation(FireStation fireStation);
    List<FireStationDTO> allPeopleInTheFireStation(Integer stationNumber);
    FireStationFinalDTO allPeopleCoveredByFireStation(List<FireStationDTO> fireStationDTOs);
}
