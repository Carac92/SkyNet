package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.PhoneAlertDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quentin_Caracatzanis
 * Implementation of Phone Alert Service.
 * Contains one method that returns a list of phone numbers based on a fire station number.
 * Get a list of the fire station address mapped with this fire station number. Then for each address generate a list of person
 * filter by the address mapped of the fire station. For each person generate a phone Alert Dto with the phone number of
 * the person. Then add the PhoneAlertDTO to the list returned.
 */
@Service
public class PhoneAlertServiceImpl implements PhoneAlertService {

    @Autowired
    private Data data;

    @Override
    public List<PhoneAlertDTO> getAllPhoneForAFireStationNumber(Integer stationNumber) {
        List<PhoneAlertDTO> phoneAlertDTOS = new ArrayList<>();
        List<FireStation> fireStations = data.getFirestations().stream()
                .filter(fireStation -> fireStation.getStation().equals(stationNumber))
                .collect(Collectors.toList());
        for (FireStation fireStation : fireStations) {
            List<Person> persons = data.getPersons().stream().filter(person -> person.getAddress().equals(fireStation.getAddress()))
                    .collect(Collectors.toList());
            for (Person person : persons) {
                PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
                phoneAlertDTO.setPhoneNumber(person.getPhone());
                phoneAlertDTOS.add(phoneAlertDTO);
            }
        }
        return phoneAlertDTOS;
    }
}
