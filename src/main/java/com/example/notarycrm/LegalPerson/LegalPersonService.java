package com.example.notarycrm.LegalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public LegalPerson get(Integer id) throws LegalPersonNot


}
