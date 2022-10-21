package com.safetynet.alert.model;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Model of Data that contains List of the different class models and generate them when the bean is created.
 */

public class Data {
    List <Person> persons;
    List <FireStation> firestations;
    List<MedicalRecord> medicalrecords;



    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
