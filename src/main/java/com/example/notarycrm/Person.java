package com.example.notarycrm;

public class Person {

    private String firstName;
    private String secondName;
    private String lastName;
    private int pesel;
    private String dayOfBirth;
    private String placeOfBrith;
    private String nationality;
    private String nameofFather;
    private String nameOfMother;
    private String address;
    private int krsNumber;
    private String companyName;

    //this constructor is for NaturalPerson class

    public Person(String firstName, String secondName, String lastName, int pesel, String dayOfBirth, String placeOfBrith, String nationality, String nameofFather, String nameOfMother, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dayOfBirth = dayOfBirth;
        this.placeOfBrith = placeOfBrith;
        this.nationality = nationality;
        this.nameofFather = nameofFather;
        this.nameOfMother = nameOfMother;
        this.address = address;
    }

    //this constructor is for NaturalPerson class

    public Person(String companyName, int krsNumber, String address){
        this.companyName = companyName;
        this.krsNumber = krsNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getPlaceOfBrith() {
        return placeOfBrith;
    }

    public void setPlaceOfBrith(String placeOfBrith) {
        this.placeOfBrith = placeOfBrith;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNameofFather() {
        return nameofFather;
    }

    public void setNameofFather(String nameofFather) {
        this.nameofFather = nameofFather;
    }

    public String getNameOfMother() {
        return nameOfMother;
    }

    public void setNameOfMother(String nameOfMother) {
        this.nameOfMother = nameOfMother;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getKrsNumber() {
        return krsNumber;
    }

    public void setKrsNumber(int krsNumber) {
        this.krsNumber = krsNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel=" + pesel +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", placeOfBrith='" + placeOfBrith + '\'' +
                ", nationality='" + nationality + '\'' +
                ", nameofFather='" + nameofFather + '\'' +
                ", nameOfMother='" + nameOfMother + '\'' +
                ", address='" + address + '\'' +
                ", krsNumber=" + krsNumber +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}


