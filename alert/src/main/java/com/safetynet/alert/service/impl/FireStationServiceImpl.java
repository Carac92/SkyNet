package com.safetynet.alert.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FireStationServiceImpl implements FireStationService {

    @Value("classpath:data.json")
    Resource resourceFile;

    @Autowired
    private Data data;


    @Override
    public FireStation updateFireStation(FireStation fireStationToUpdate) throws IOException {
        List<FireStation> fireStations = data.getFirestations();
        int i = 0;
        for(FireStation fireStation : data.getFirestations()) {
            i++;
            if(fireStation.getAddress().equals(fireStationToUpdate.getAddress())) {
                fireStations.add(fireStation);
                data.setFirestations(fireStations);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File("src/main/resources/data.json"), data);
                return fireStationToUpdate;
            }
        }
        return null;
    }

    @Override
    public List<FireStation> addFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();
        if(!fireStations.contains(fireStation)) {
           fireStations.add(fireStation);
           data.setFirestations(fireStations);
           return fireStations;
        }
        return fireStations;
        }

    @Override
    public boolean removeFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();
        if(fireStations.contains(fireStation)) {
            fireStations.remove(fireStation);
            data.setFirestations(fireStations);
            return true;
        }
        return false;
    }
}
