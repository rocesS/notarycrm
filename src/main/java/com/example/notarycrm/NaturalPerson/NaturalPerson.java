package com.example.notarycrm.naturalperson;

import jakarta.persistence.*;

@Entity
@Table(name = "natural_persons")

public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 45, name = "first_name")
    private String firstName;

    @Column (nullable = false, length = 45, name = "last_name")
    private String lastName;

    @Column (nullable = false, length = 45, unique = true)
    private String pesel;

    @Column (nullable = false, length = 45, name = "date_of_birth")
    private String dateOfBirth;

    @Column (nullable = false, length = 45, name = "place_of_birth")
    private String cityOfBirth;

    @Column (nullable = false, length = 45)
    private String address;

    @Column (nullable = false, length = 45, name = "mothers_name")
    private String nameOfMother;

    @Column (nullable = false, length = 45, name = "fathers_name")
    private String nameOfFather;

    @Column (nullable = false, length = 45, unique = true, name = "id_card_number")
    private String iDCardNumber;

    @Column (nullable = false, length = 45)
    private String nationality;

    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOfMother() {
        return nameOfMother;
    }

    public void setNameOfMother(String nameOfMother) {
        this.nameOfMother = nameOfMother;
    }

    public String getNameOfFather() {
        return nameOfFather;
    }

    public void setNameOfFather(String nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public String getiDCardNumber() {
        return iDCardNumber;
    }

    public void setiDCardNumber(String iDCardNumber) {
        this.iDCardNumber = iDCardNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "NaturalPerson{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", pesel='" + getPesel() + '\'' +
                ", dateOfBirth='" + getDateOfBirth() + '\'' +
                ", cityOfBirth='" + getCityOfBirth() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", nameOfMother='" + getNameOfMother() + '\'' +
                ", nameOfFather='" + getNameOfFather() + '\'' +
                ", iDCardNumber='" + getiDCardNumber() + '\'' +
                ", nationality='" + getNationality() + '\'' +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
