package com.safetynet.alert.service;

import com.safetynet.alert.model.Person;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Person service.
 * Generate a Post / Put / Delete Methods.
 */
public interface PersonService {

    boolean updatePerson(Person personToUpdate);
    boolean addPerson(Person person);

    boolean deletePerson(Person person);
}
