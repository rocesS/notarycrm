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

    public Person(String companyName, int krsNumber, String address){
        this.companyName = companyName;
        this.krsNumber = krsNumber;
        this.address = address;
    }


}


