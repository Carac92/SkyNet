package com.safetynet.alert.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.safetynet.alert.util.AgeCalculator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class MedicalRecord {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthdate;
    private List<String> medications;
    private List<String> allergies;
    private Integer age;

/*
    Constructors of Medical Record model.
 */
    public MedicalRecord() {
    }

    public MedicalRecord(String firstName, String lastName, Date birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
        this.age = getAge();
    }

    public String getFirstName() {
        return firstName;
    }

    /*
    Getters and Setters for medical record model.
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    // if age is not set it's setting age with age calculator Method.
    public Integer getAge() {
            setAge();
            return age;
    }

    public void setAge() {
        if(this.birthdate == null) {
            this.age = null;
        }else {
            this.age = AgeCalculator.calculateAge(this.birthdate);
        }
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", medications=" + medications +
                ", allergies=" + allergies +
                ", age=" + getAge() +
                '}';
    }
}