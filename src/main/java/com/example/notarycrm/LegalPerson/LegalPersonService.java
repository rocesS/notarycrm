package com.example.notarycrm.LegalPerson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonService {
    @Autowired private LegalPersonRepository repo;

    public List<LegalPerson> listAll() {
        return  (List<LegalPerson>) repo.findAll();
    }
}
