package com.myriamcounilh.safetynetalerts.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MedicalRecord {

    @NotNull(message = "firstName obligatory")
    private String firstName;

    @NotNull(message = "lastName obligatory")
    private String lastName;

    private String birthdate;

    private String[] medications;

    private String[] allergies;

    public MedicalRecord () {  }

    public MedicalRecord (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord medicalRecord = (MedicalRecord) o;
        return firstName.equals(medicalRecord.firstName) && lastName.equals(medicalRecord.lastName);
    }

    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public String toString() {
        return "MedicalRecord {" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
              '}';
    }

}
