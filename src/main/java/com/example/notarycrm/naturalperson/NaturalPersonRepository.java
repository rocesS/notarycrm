package com.example.notarycrm.naturalperson;

import org.springframework.data.repository.CrudRepository;

public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Integer> {
    Long countById(Integer id);
}
