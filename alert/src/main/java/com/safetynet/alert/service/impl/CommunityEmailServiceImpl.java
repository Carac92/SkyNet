package com.safetynet.alert.service.impl;

import com.safetynet.alert.dto.CommunityEmailDTO;
import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.CommunityEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quentin_Caracatzanis
 * Implementation of CommunityEmailService
 * Has one method that returns a List of the CommunityEmailDTO based on a city.
 * The algorithm get the email of the persons that live in the city in parameters.
 */
@Service
public class CommunityEmailServiceImpl implements CommunityEmailService {
    @Autowired
    private Data data;

    @Override
    public List<CommunityEmailDTO> getAllEmailOfCity(String city) {
        List<CommunityEmailDTO> result = new ArrayList<>();
        List<Person> persons = data.getPersons().stream().filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
        for(Person person : persons) {
            CommunityEmailDTO dto = new CommunityEmailDTO();
            dto.setEmail(person.getEmail());
            result.add(dto);
        }
        return result;
    }
}
