package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private Data data;

    @Override
    public List<PersonInfoDTO> getPersonInfoByName(String firstName, String lastName) {
        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();
        List<Person> persons = data.getPersons().stream().filter(person -> person.getFirstName().equals(firstName)
                &&person.getLastName().equals(lastName)).collect(Collectors.toList());
        for(Person person : persons) {
            List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream()
                    .filter(record -> record.getFirstName().equals(firstName)&& record.getLastName().equals(lastName))
                    .collect(Collectors.toList());
            for(MedicalRecord record : medicalRecords) {
                PersonInfoDTO dto = new PersonInfoDTO();
                dto.setEmail(person.getEmail());
                dto.setFirstName(record.getFirstName());
                dto.setLastName(record.getLastName());
                dto.setMedications(record.getMedications());
                dto.setAllergies(record.getAllergies());
                dto.setAge(record.getAge());
                dto.setAddress(person.getAddress());
                personInfoDTOS.add(dto);
            }
        }
        return personInfoDTOS;
    }
}
