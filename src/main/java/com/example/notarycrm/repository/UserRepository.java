package com.example.notarycrm.repository;

import com.example.notarycrm.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
}
