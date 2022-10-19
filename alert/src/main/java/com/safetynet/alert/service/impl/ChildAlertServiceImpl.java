package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.ChildAlertDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.ChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertServiceImpl implements ChildAlertService {

    @Autowired
    private Data data;

    @Override
    public List<ChildAlertDTO> findChildOfAnAddress(String address) {
        List<ChildAlertDTO> childAlertDTOs = new ArrayList<>();
        List<Person> persons= data.getPersons().stream()
                .filter(person -> person.getAddress().equals(address)).collect(Collectors.toList());

        //CollectChildren
        for(Person person : persons) {
            List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream()
                    .filter(record -> record.getFirstName().equals(person.getFirstName())
                            &&record.getLastName().equals(person.getLastName())).collect(Collectors.toList());
            for(MedicalRecord medicalRecord : medicalRecords){
                if (medicalRecord.getAge()<18){
                    if((person.getLastName().equals(medicalRecord.getLastName()))&&(person.getFirstName().equals(medicalRecord.getFirstName()))){
                        ChildAlertDTO child = new ChildAlertDTO();
                        child.setFirstName(medicalRecord.getFirstName());
                        child.setLastName(medicalRecord.getLastName());
                        child.setAddress(person.getAddress());
                        child.setAge(medicalRecord.getAge());
                        childAlertDTOs.add(child);
                        }
                    }
                }
            }
        //CollectPersons of the same address for each child
        for(ChildAlertDTO child : childAlertDTOs){
            List<String> personOfTheSameAddress = new ArrayList<String>();
            for(Person person : persons){
                if(child.getAddress().equals(person.getAddress()))
                    personOfTheSameAddress.add(person.getLastName()+ " " + person.getFirstName());
                }
            child.setPersonsOfTheSameAddress(personOfTheSameAddress);
        }
        return childAlertDTOs;
        }
    }
