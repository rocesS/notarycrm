package com.example.notarycrm.legalperson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated //zeby obsluzyc  @Pattern(regexp = "\\d{10}", message = "KRS number must consist of 10 digits")
public class LegalPersonService {

    private final LegalPersonRepository repo;
    private final KrsValidatorService krsValidatorService;

    @Autowired
    public LegalPersonService(LegalPersonRepository repo, KrsValidatorService krsValidatorService) {
        this.repo = repo;
        this.krsValidatorService = krsValidatorService;
    }

    public List<LegalPerson> legalPersonList() {
        return  (List<LegalPerson>) repo.findAll();
    }

    public void save(LegalPerson legalPerson) throws KrsValidationException, DuplicateKrsException {
        if (!krsValidatorService.validateKrsNumber(legalPerson.getKrsNumber())) {
            throw new KrsValidationException("Invalid KRS number: " + legalPerson.getKrsNumber());
        }

        Optional<LegalPerson> existingPersonWithKrs = repo.findByKrsNumberAndIdNot(legalPerson.getKrsNumber(), legalPerson.getId());
        if (existingPersonWithKrs.isPresent() && !existingPersonWithKrs.get().getId().equals(legalPerson.getId())) {
            throw new DuplicateKrsException("KRS number: " + legalPerson.getKrsNumber() +  " already exists and is assigned to other legal person");
        }

        repo.save(legalPerson);
    }

    public LegalPerson get(Integer id) throws LegalPersonNotFoundException {
        Optional<LegalPerson> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new LegalPersonNotFoundException("Could not find any Legal Person with ID");
    }


    public void delete(Integer id) throws LegalPersonNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new LegalPersonNotFoundException("Could not find any Legal Person with ID " + id);
        }
        repo.deleteById(id);
    }
}
