package com.safetynet.alert.service.impl;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements com.safetynet.alert.service.PersonService {


    @Autowired
    private Data data;

    @Override
    public boolean updatePerson(Person personToUpdate) {
        List<Person> persons = data.getPersons();
        int i=0;
        for (Person p : persons) {
            i++;
            if((p.getFirstName().equals(personToUpdate.getFirstName()))
                    &&(p.getLastName().equals(personToUpdate.getLastName()))){
                persons.add(i,personToUpdate);
                data.setPersons(persons);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addPerson(Person person) {
        List<Person> persons = data.getPersons();
        if (!persons.contains(person)) {
            persons.add(person);
            data.setPersons(persons);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePerson(Person personToDelete) {
        List<Person> persons = data.getPersons();
        int i = 0;
        for (Person p : persons){
            i++;
            if((p.getLastName().equals(personToDelete.getLastName()))&&(p.getFirstName().equals(personToDelete.getFirstName()))){
                persons.remove(i);
                data.setPersons(persons);
                return true;
            }
        }
        return false;
    }
}
