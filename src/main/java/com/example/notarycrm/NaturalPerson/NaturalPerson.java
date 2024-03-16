package com.example.notarycrm.NaturalPerson;

import jakarta.persistence.*;

@Entity
@Table(name = "natural_persons")

public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 45)
    private String firstName;

    @Column (nullable = false, length = 45)
    private String lastName;

    @Column (nullable = false, length = 45, unique = true)
    private Integer pesel;

    @Column (nullable = false, length = 45)
    private String dateOfBirth;

    @Column (nullable = false, length = 45)
    private String cityOfBirth;

    @Column (nullable = false, length = 45)
    private String address;

    @Column (nullable = false, length = 45)
    private String nameOfMother;

    @Column (nullable = false, length = 45)
    private String nameOfFather;

    @Column (nullable = false, length = 45, unique = true)
    private String iDCardNumber;

    @Column (nullable = false, length = 45)
    private String validityDateOfTheIDCard;

    @Column (nullable = false, length = 45)
    private String nationality;


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

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
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

    public String getValidityDateOfTheIDCard() {
        return validityDateOfTheIDCard;
    }

    public void setValidityDateOfTheIDCard(String validityDateOfTheIDCard) {
        this.validityDateOfTheIDCard = validityDateOfTheIDCard;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
