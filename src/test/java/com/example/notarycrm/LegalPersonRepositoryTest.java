package com.example.notarycrm;

import com.example.notarycrm.LegalPerson.LegalPerson;
import com.example.notarycrm.LegalPerson.LegalPersonRepository;
import org.apache.catalina.User;
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

public class LegalPersonRepositoryTest {
    @Autowired private LegalPersonRepository repo;

    @Test
    public void testAdd() {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setEmail("email.napisz@domnie.pl");
        legalPerson.setAddress("Ul. Kocia 1, Wojnów 51-045");
        legalPerson.setName("WKS Sp z o.o.");
        legalPerson.setKrsNumber(523623564);
        legalPerson.setPhoneNumber("+48748523452");

        LegalPerson savedLegalPerson = repo.save(legalPerson);

        Assertions.assertThat(savedLegalPerson).isNotNull();
        Assertions.assertThat(savedLegalPerson.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<LegalPerson> legalPersons = repo.findAll();
        Assertions.assertThat(legalPersons).hasSizeGreaterThan(0);

        for (LegalPerson legalPerson : legalPersons) {
            System.out.println(legalPerson);
        }
    }

    @Test
    public void testUpdate() {
        Integer legalPersonId = 1;
        Optional<LegalPerson> optionalLegalPerson = repo.findById(legalPersonId);
        LegalPerson legalPerson = optionalLegalPerson.get();
        legalPerson.setEmail("marian@buziak.pl");
        repo.save(legalPerson);

        LegalPerson updateLegalPerson = repo.findById(legalPersonId).get();
        Assertions.assertThat(updateLegalPerson.getEmail()).isEqualTo("marian@buziak.pl");
    }

    @Test
    public void testGet() {
        Integer legalPersonId = 2;
        Optional<LegalPerson> optionalLegalPerson = repo.findById(legalPersonId);

        Assertions.assertThat(optionalLegalPerson).isPresent();
        System.out.println(optionalLegalPerson.get());
    }

    @Test
    public void testDelete() {
        Integer legalPersonId = 2;
        repo.deleteById(legalPersonId);

        Optional<LegalPerson> optionalLegalPerson = repo.findById(legalPersonId);
        Assertions.assertThat(optionalLegalPerson).isNotPresent();
    }

}
