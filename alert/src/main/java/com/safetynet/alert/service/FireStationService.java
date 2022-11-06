package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.FireStation;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the fire station service.
 * Generate a get method for a specific endpoint(which need the result of the second get method.
 * Generate also a Post / Put / Delete Methods.
 */
public interface FireStationService {

    boolean updateFireStation(String address,FireStation fireStationToUpdate);
    boolean addFireStation(FireStation fireStation);
    boolean removeFireStation(Integer number, String address);
    List<FireStationDTO> getAllPeopleInTheFireStation(Integer stationNumber);
    FireStationFinalDTO GetAllPeopleCoveredByFireStation(List<FireStationDTO> fireStationDTOs);
}
