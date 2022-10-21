package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quentin_Caracatzanis
 * Implementation of FireStationService
 * Autowired to the Bean of Data. Generate a get/post/delete methods for FireStation that needs a firestation in parameter.
 * Generate a get method that return a List of FireStationDTO.
 * The algorithm of the get method use the number of the fire station and get for each address the persons mapped by the
 * fire station and some of their medical record information. It returns also the number of minor and adults of that list.
 */
@Service
public class FireStationServiceImpl implements FireStationService {
    @Autowired
    private Data data;


    @Override
    public boolean updateFireStation(FireStation fireStationToUpdate){
        List<FireStation> fireStations = data.getFirestations();
        int i = 0;
        for(FireStation fireStation : data.getFirestations()) {
            if(fireStation.getAddress().equals(fireStationToUpdate.getAddress())) {
                fireStations.set(i, fireStationToUpdate);
                data.setFirestations(fireStations);
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public boolean addFireStation(FireStation fireStation) {
        List<FireStation> fireStations = data.getFirestations();
        Iterator<FireStation> it = fireStations.iterator();
        while(it.hasNext()) {
            FireStation fs = it.next();
            if(fs.getAddress().equals(fireStation.getAddress())) {
                return false;
            }
        }
        fireStations.add(fireStation);
        data.setFirestations(fireStations);
        return true;
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

    @Override
    public List<FireStationDTO> getAllPeopleInTheFireStation(Integer stationNumber) {
        List<FireStationDTO> fireStationDTOS = new ArrayList<>();
        List<FireStation> fireStations = data.getFirestations().stream()
                .filter(fireStation -> fireStation.getStation().equals(stationNumber)).collect(Collectors.toList());
        for(FireStation fireStation : fireStations){
            List<Person> persons = data.getPersons().stream()
                    .filter(person -> person.getAddress().equals(fireStation.getAddress())).collect(Collectors.toList());
            for (Person person : persons) {
                List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream()
                        .filter(record -> record.getLastName().equals(person.getLastName())
                                &&record.getFirstName().equals(person.getFirstName())).collect(Collectors.toList());
                for(MedicalRecord medicalRecord : medicalRecords){
                    FireStationDTO dto = new FireStationDTO();
                    dto.setFirstName(medicalRecord.getFirstName());
                    dto.setLastName(medicalRecord.getLastName());
                    dto.setAddress(person.getAddress());
                    dto.setPhoneNumber(person.getPhone());
                    if(medicalRecord.getAge()<18)dto.setMinor(true);
                    fireStationDTOS.add(dto);
                }
            }
        }
        return fireStationDTOS;
    }
// Method that return the Final FireStationDTO object that contains the List of FireStationDTO and the number of minor
// and the number of adults.
    @Override
    public FireStationFinalDTO GetAllPeopleCoveredByFireStation(List<FireStationDTO> fireStationDTOs) {
        FireStationFinalDTO dto = new FireStationFinalDTO();
        dto.setFireStationDTOS(fireStationDTOs);
        dto.setNumberOfMinor(getNumberOfMinor(fireStationDTOs));
        dto.setNumberOfAdults(getNumberOfAdults(dto.getNumberOfMinor()));
        return dto;
    }
// Method that return the number of minor based on the List of FireStationDTO.
// This method is used by GetAllPeopleCoveredByFireStation.


    public Integer getNumberOfMinor(List<FireStationDTO> fireStationDTOs) {
        int minor = 0;
        for(FireStationDTO dto : fireStationDTOs) {
            if(dto.isMinor() == true) minor++;
        }
        return minor;
    }

// Method that return the number of Adults based on the number of minor.
// This method is used by GetAllPeopleCoveredByFireStation.
    public Integer getNumberOfAdults(Integer numberOfMinor) {
        return data.getPersons().size() - numberOfMinor;
    }
}
