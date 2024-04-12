package com.example.notarycrm.LegalPerson;

import jakarta.persistence.*;

@Entity
@Table(name = "legal_persons")
public class LegalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 75, name = "name")
    private String name;

    @Column(nullable = false, length = 45, name = "email")
    private String email;

    @Column(nullable = false, length = 45, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, length = 45, name = "address")
    private String address;

    @Column(unique = true, nullable = false, length = 45, name = "krs")
    private String krsNumber;

    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKrsNumber() {
        return krsNumber;
    }

    public void setKrsNumber(String krsNumber) {
        this.krsNumber = krsNumber;
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", krsNumber=" + getKrsNumber() +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}