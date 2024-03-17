package com.example.notarycrm;

import com.example.notarycrm.LegalPerson.LegalPerson;
import com.example.notarycrm.NaturalPerson.NaturalPerson;
import com.example.notarycrm.NaturalPerson.NaturalPersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class NaturalPersonRepositoryTest {
    @Autowired private NaturalPersonRepository repo;

    @Test
    public void testAdd() {
        NaturalPerson naturalPerson = new NaturalPerson();
        naturalPerson.setFirstName("Barbara");
        naturalPerson.setLastName("Momot");
        naturalPerson.setPesel("70101078456");
        naturalPerson.setDateOfBirth("10.10.1970");
        naturalPerson.setCityOfBirth("Monachium");
        naturalPerson.setAddress("ul. Złota 25/27, 81-704 ");
        naturalPerson.setNameOfMother("Doris");
        naturalPerson.setNameOfFather("Manfred");
        naturalPerson.setiDCardNumber("FGR 34556");
        naturalPerson.setNationality("niemieckie");

        NaturalPerson savedNaturalPerson = repo.save(naturalPerson);

        Assertions.assertThat(savedNaturalPerson).isNotNull();
        Assertions.assertThat(savedNaturalPerson.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<NaturalPerson> naturalPersons = repo.findAll();
        Assertions.assertThat(naturalPersons).hasSizeGreaterThan(0);

        for (NaturalPerson naturalPerson : naturalPersons) {
            System.out.println(naturalPersons);
        }
    }

    @Test
    public void testUpdate() {
        Integer naturalPersonId = 1;
        Optional<NaturalPerson> optionalNaturalPerson = repo.findById(naturalPersonId);
        NaturalPerson naturalPerson = optionalNaturalPerson.get();
        naturalPerson.setLastName("Yamaha");

        repo.save(naturalPerson);
        NaturalPerson updateegNaturalPerson = repo.findById(naturalPersonId).get();
        Assertions.assertThat(updateegNaturalPerson.getLastName()).isEqualTo("Yamaha");

    }

    @Test
    public void testGet() {
        Integer naturalPersonId = 2;
        repo.deleteById(2);

        Optional<NaturalPerson> optionalNaturalPerson = repo.findById(naturalPersonId);
        Assertions.assertThat(optionalNaturalPerson).isNotPresent();
    }

    @Test
    public void testDelete() {
        Integer naturalPersonId = 2;
        repo.deleteById(2);

        Optional<NaturalPerson> optionalNaturalPerson = repo.findById(2);
        Assertions.assertThat(optionalNaturalPerson).isNotPresent();
    }

}
