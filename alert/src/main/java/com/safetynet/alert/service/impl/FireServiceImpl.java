package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quentin_Caracatzanis
 * Implementation of FireService
 * Contains one method that returns a List of FireDTO based on an address.
 * The algorithm get a list of the firestation that deserve the address.
 * Then the algorithm get a list of Person at that address and get their Medical Record.
 * Finish by generating a List of FireDTO with MR information, person information and fire station number.
 */
@Service
public class FireServiceImpl implements FireService {

    @Autowired
    private Data data;

    @Override
    public List<FireDTO> getAllPeopleByFireStationAddress(String address) {
        List<FireDTO> fireDTOS = new ArrayList<>();
        List<FireStation> fireStations = data.getFirestations().stream()
                .filter(fireStation -> fireStation.getAddress().equals(address)).collect(Collectors.toList());
        for(FireStation fireStation : fireStations) {
            List<Person> persons = data.getPersons().stream()
                    .filter(person -> person.getAddress().equals(fireStation.getAddress())).collect(Collectors.toList());
            for(Person person : persons) {
                List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream()
                        .filter(record -> record.getLastName().equals(person.getLastName()) && record.getFirstName()
                                .equals(person.getFirstName())).collect(Collectors.toList());
                for (MedicalRecord medicalRecord : medicalRecords) {
                    FireDTO dto = new FireDTO();
                    dto.setFirstName(medicalRecord.getFirstName());
                    dto.setLastName(medicalRecord.getLastName());
                    dto.setAllergies(medicalRecord.getAllergies());
                    dto.setAge(medicalRecord.getAge());
                    dto.setStationNumber(fireStation.getStation());
                    dto.setMedications(medicalRecord.getMedications());
                    dto.setPhoneNumber(person.getPhone());
                    fireDTOS.add(dto);
                }
            }
        }
        return fireDTOS;
    }
}
