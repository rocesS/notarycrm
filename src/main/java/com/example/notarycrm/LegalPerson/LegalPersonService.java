package com.example.notarycrm.LegalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalPersonService {

    @Autowired
    private LegalPersonRepository repo;

    public List<LegalPerson> legalPersonList() {
        return  (List<LegalPerson>) repo.findAll();
    }

    public void save(LegalPerson legalPerson) {
        repo.save(legalPerson);
    }

    public LegalPerson get(Integer id) throws LegalPersonNotFoundException {
        Optional<LegalPerson> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new LegalPersonNotFoundException("Could not find any legal person with ID");
    }


    public void delete(Integer id) throws LegalPersonNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new LegalPersonNotFoundException("Could not find any Legal Person with ID " + id);
        }
        repo.deleteById(id);
    }
}
