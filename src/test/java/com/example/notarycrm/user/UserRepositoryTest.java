package com.example.notarycrm.user;

import com.example.notarycrm.model.User;
import com.example.notarycrm.repository.UserRepository;
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

public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAdd() {
        User user = new User();
        user.setLogin("userek");
        user.setPassword("niedozlamania");
        user.setFirstName("Marcin");
        user.setLastName("Daniec");
        user.setEmail("power@onet.com");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setEmail("test1@test.pl");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("test1@test.pl");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }




}
