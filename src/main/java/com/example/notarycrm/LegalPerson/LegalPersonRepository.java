package com.example.notarycrm.LegalPerson;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LegalPersonRepository extends CrudRepository<LegalPerson, Integer>  {
    Long countById(Integer id);
    //wyszukanie po KRS podanym przez użytkownika
    Optional<LegalPerson> findByKrsNumberAndIdNot(String krsNumber, Integer id);
}
