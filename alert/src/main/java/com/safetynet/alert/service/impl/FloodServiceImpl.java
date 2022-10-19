package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.FloodDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloodServiceImpl implements FloodService {

    @Autowired
    private Data data;

    @Override
    public List<FloodDTO> getAllHousesByAListOfStationNumber(List<Integer> stationsNumber) {
        List<FloodDTO> floodDTOs = new ArrayList<FloodDTO>();
        for (Integer stationNumber : stationsNumber) {
            List<FireStation> fireStations = data.getFirestations().stream()
                    .filter(fireStation -> fireStation.getStation().equals(stationNumber)).collect(Collectors.toList());
            for (FireStation fireStation : fireStations) {
                List<Person> persons = data.getPersons().stream()
                        .filter(person -> person.getAddress().equals(fireStation.getAddress())).collect(Collectors.toList());
                for (Person person : persons) {
                    List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream()
                            .filter(record -> record.getFirstName().equals(person.getFirstName())
                                    &&record.getLastName().equals(person.getLastName())).collect(Collectors.toList());
                    for (MedicalRecord medicalRecord : medicalRecords) {
                        FloodDTO dto = new FloodDTO();
                        dto.setStationNumber(fireStation.getStation());
                        dto.setFirstName(medicalRecord.getFirstName());
                        dto.setLastName(medicalRecord.getLastName());
                        dto.setAddress(person.getAddress());
                        dto.setPhoneNumber(person.getPhone());
                        dto.setAge(medicalRecord.getAge());
                        dto.setAllergies(medicalRecord.getAllergies());
                        dto.setMedications(medicalRecord.getMedications());
                        floodDTOs.add(dto);
                    }
                }
            }
        }
        return floodDTOs;
    }
}
