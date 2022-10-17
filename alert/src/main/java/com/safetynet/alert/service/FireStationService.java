package com.safetynet.alert.service;

import com.safetynet.alert.model.FireStation;

import java.io.IOException;
import java.util.List;

public interface FireStationService {

    FireStation updateFireStation(FireStation fireStationToUpdate) throws IOException;
   List<FireStation> addFireStation(FireStation fireStation);
    boolean removeFireStation(FireStation fireStation);
}
