package com.safetynet.alert.service.impl;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Implementation of Person Service.
 * Contains 3 methods one for adding a person to the list of person in data, one for updating , the last one for deleting.
 */
@Service
public class PersonServiceImpl implements com.safetynet.alert.service.PersonService {


    @Autowired
    private Data data;

    @Override
    public boolean updatePerson(String firstName, String lastName, Person personToUpdate) {
        List<Person> persons = data.getPersons();
        int i=0;
        for (Person p : persons) {
            if((p.getFirstName().equals(firstName)) && (p.getLastName().equals(lastName))){
                persons.add(i,personToUpdate);
                data.setPersons(persons);
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public boolean addPerson(Person personToAdd) {
        List<Person> persons = data.getPersons();
        Iterator<Person> it = persons.iterator();
        while(it.hasNext()) {
            Person person = it.next();
            if (!person.getFirstName().equals(personToAdd.getFirstName()) ||
                    !person.getLastName().equals(personToAdd.getLastName())) {
                persons.add(person);
                data.setPersons(persons);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePerson(String firstName, String lastName) {
        List<Person> persons = data.getPersons();
        Iterator<Person> it = persons.iterator();
        while(it.hasNext()) {
            Person person = it.next();
            if(person.getFirstName().equals(firstName) &&
                    person.getLastName().equals(lastName)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
