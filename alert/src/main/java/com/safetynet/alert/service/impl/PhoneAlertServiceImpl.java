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
