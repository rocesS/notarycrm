package com.example.notarycrm.NaturalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaturalPersonService {

    @Autowired
    private NaturalPersonRepository repo;

    public List<NaturalPerson> naturalPersonList() {
        return (List<NaturalPerson>) repo.findAll();
    }


}
