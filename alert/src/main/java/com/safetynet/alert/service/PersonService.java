package com.safetynet.alert.service;

import com.safetynet.alert.model.Person;

import java.util.List;

public interface PersonService {

    boolean updatePerson(Person personToUpdate);
    boolean addPerson(Person person);

    boolean deletePerson(Person person);
}
