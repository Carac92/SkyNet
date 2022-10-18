package com.safetynet.alert.service.impl;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;

@Service
public class FireStationServiceImpl implements FireStationService {
    @Autowired
    private Data data;


    @Override
    public FireStation updateFireStation(FireStation fireStationToUpdate){
        List<FireStation> fireStations = data.getFirestations();
        int i = 0;
        for(FireStation fireStation : data.getFirestations()) {
            i++;
            if(fireStation.getAddress().equals(fireStationToUpdate.getAddress())) {
                fireStations.add(fireStation);
                data.setFirestations(fireStations);
                return fireStationToUpdate;
            }
        }
        return null;
    }

    @Override
    public boolean addFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();
        Iterator<FireStation> it = fireStations.iterator();
        while(it.hasNext()) {
            FireStation fs = it.next();
            if(!fs.getAddress().equals(fireStation.getAddress())) {
                fireStations.add(fireStation);
                data.setFirestations(fireStations);
                return true;
            }
        }
        return false;
        }

    @Override
    public boolean removeFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();
        Iterator<FireStation> it = fireStations.iterator();
        while(it.hasNext()) {
            FireStation fs = it.next();
            if(fs.getAddress().equals(fireStation.getAddress())) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
