package com.example.notarycrm.LegalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LegalPersonController {
    @Autowired private LegalPersonService service;

    @GetMapping("/legalpersons")
    public String showLegalPersonList(Model model) {
        List<LegalPerson> listLegalPersons = service.listAll();
        model.addAttribute("listLegalPersons",listLegalPersons);

        return "legalPersons";
    }


}
