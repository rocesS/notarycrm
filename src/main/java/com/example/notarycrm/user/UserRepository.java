package com.example.notarycrm.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
}
