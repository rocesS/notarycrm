package com.example.notarycrm.naturalperson;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository repo;

    public NaturalPersonService(NaturalPersonRepository repo) {
        this.repo = repo;
    }

    public List<NaturalPerson> naturalPersonList() {
        return (List<NaturalPerson>) repo.findAll();
    }

    public void save(NaturalPerson naturalPerson) {
        repo.save(naturalPerson);
    }

    public NaturalPerson get(Integer id) throws NaturalPersonNotFoundException {
        Optional<NaturalPerson> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw  new NaturalPersonNotFoundException("Could not find any Natural Person with ID");
    }

    public void delete(Integer id) throws NaturalPersonNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new NaturalPersonNotFoundException("Could not find any user with ID");
        }
        repo.deleteById(id);
    }

}
