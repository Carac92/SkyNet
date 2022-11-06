package com.safetynet.alert.service;

import com.safetynet.alert.model.Person;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Person service.
 * Generate a Post / Put / Delete Methods.
 */
public interface PersonService {

    boolean updatePerson(String firstName, String lastName, Person personToUpdate);
    boolean addPerson(Person person);

    boolean deletePerson(String firstName, String lastName);
}
