package com.example.notarycrm.NaturalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NaturalPersonController {

    @Autowired
    private NaturalPersonService service;

    @GetMapping("/naturalpersons")
    public String showNaturalPersonList(Model model) {
        List<NaturalPerson> naturalPersonsList = service.naturalPersonList();
        model.addAttribute("naturalPersonsList",naturalPersonsList);
        return "naturalpersons";
    }

}
