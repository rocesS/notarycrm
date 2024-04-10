package com.example.notarycrm.LegalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LegalPersonController {

    @Autowired
    private LegalPersonService service;

    @GetMapping("/legalpersons")
    public String showLegalPersonList(Model model) {
        List<LegalPerson> listLegalPersons = service.legalPersonList();
        model.addAttribute("listLegalPersons",listLegalPersons);
        return "legalpersons";
    }

    @GetMapping("/legalpersons/new")
    public String showNewForm(Model model) {
        model.addAttribute("legalperson", new LegalPerson());
        model.addAttribute("pageTitle", "Add new Legal Person");
        return "legalperson_form";
    }

    @PostMapping("/legalpersons/save")
    public String saveLegalPerson(LegalPerson legalPerson, RedirectAttributes ra) {
        service.save(legalPerson);
        ra.addFlashAttribute("message","Legal Person has been saved successfully");
        return "redirect:/legalpersons";
    }

    @GetMapping("/legalpersons/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            LegalPerson legalPerson = service.get(id);
            model.addAttribute("legalperson",legalPerson);
            model.addAttribute("pageTitle", "Edit Legal Person (ID: " + id + ")");
            return "legalperson_form";
        } catch (LegalPersonNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/legalpersons";
        }
    }

    @GetMapping("/legalpersons/delete/{id}")
    public String deleteLegalPerson(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            LegalPerson legalPerson = service.get(id);
            service.delete(id);
            ra.addFlashAttribute("message", legalPerson.getName() + " has been deleted");
        } catch (LegalPersonNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/legalpersons";
    }

}
