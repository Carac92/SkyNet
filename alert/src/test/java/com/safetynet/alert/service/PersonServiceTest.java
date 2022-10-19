package com.safetynet.alert.service;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    Data data;

    @BeforeEach
    public void setUp(){
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512","jaboyd@email.com");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        when(data.getPersons()).thenReturn(persons);
    }

    @Test
    public void addPersonTest(){
        // GIVEN


        // WHEN
        boolean result = personService.addPerson(new Person("Test", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO"));

        // THEN
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void updatePersonTest(){

        // WHEN
        boolean result = personService.updatePerson(new Person("John", "Boyd", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO", "testGetFireDTO"));

        // THEN

        assertThat(result).isEqualTo(true);
    }
    @Test
    public void deletePersonTest(){
        boolean result = personService.deletePerson(new Person("John", "Boyd", "", "", "", "", ""));

        // THEN
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void deletePersonThatDoesNotExistTest(){
        boolean result = personService.deletePerson(new Person("t", "t", "", "", "", "", ""));

        //then
        assertThat(result).isEqualTo(false);
    }
    @Test
    public void updatePersonThatDoesNotExistTest(){
        boolean result = personService.updatePerson(new Person());

        assertThat(result).isEqualTo(false);
    }
    @Test
    public void addPersonThatAlreadyExistsTest(){
        boolean result = personService.addPerson(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512","jaboyd@email.com"));

        assertThat(result).isEqualTo(false);
    }
}
