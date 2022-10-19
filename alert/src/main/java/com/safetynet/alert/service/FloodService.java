package com.safetynet.alert.service;

import com.safetynet.alert.dto.FloodDTO;

import java.util.List;

public interface FloodService {
    List<FloodDTO> getAllHousesByAListOfStationNumber(List<Integer> stationsNumber);
}
