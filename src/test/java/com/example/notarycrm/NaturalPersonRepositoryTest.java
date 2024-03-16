package com.example.notarycrm;

import com.example.notarycrm.NaturalPerson.NaturalPerson;
import com.example.notarycrm.NaturalPerson.NaturalPersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class NaturalPersonRepositoryTest {
    @Autowired private NaturalPersonRepository repo;

    @Test
    public void testAdd() {
        NaturalPerson naturalPerson = new NaturalPerson();
        naturalPerson.setCityOfBirth("Wrocław");
        naturalPerson.setDateOfBirth("16.12.1980");
        naturalPerson.setFirstName("Magda");
        naturalPerson.setLastName("Burska");
        naturalPerson.setNationality("polskie");
        naturalPerson.setPesel(80121689756);


    }



}
