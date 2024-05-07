package com.example.notarycrm.repository;

import com.example.notarycrm.model.NaturalPerson;
import org.springframework.data.repository.CrudRepository;

public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Integer> {
    Long countById(Integer id);
}
