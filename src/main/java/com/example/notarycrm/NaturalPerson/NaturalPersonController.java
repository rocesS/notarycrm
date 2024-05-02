package com.example.notarycrm.NaturalPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/naturalpersons/new")
    public String showNewForm(Model model) {
        model.addAttribute("naturalPerson", new NaturalPerson());
        model.addAttribute("pageTitle", "Add new Natural Person");
        return "naturalperson_form";
    }

    @PostMapping("/naturalpersons/save")
    public String saveNaturalPerson(NaturalPerson naturalPerson, RedirectAttributes ra) {
        service.save(naturalPerson);
        ra.addFlashAttribute("message", "Natural Person has been saved successfully");
        return "redirect:/naturalpersons";
    }

    @GetMapping("/naturalpersons/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            NaturalPerson naturalPerson = service.get(id);
            model.addAttribute("naturalPerson", naturalPerson);
            model.addAttribute("pageTitle","Edit Natural Person (ID: " + id + ")");
            return "naturalperson_form";
        } catch (NaturalPersonNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/naturalpersons";
        }
    }

    @GetMapping("/naturalpersons/delete/{id}")
    public String deleteNaturalPerson(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            NaturalPerson naturalPerson = service.get(id);
            service.delete(id);
            ra.addFlashAttribute("message",naturalPerson.getFirstName() + " " + naturalPerson.getLastName() + " has been deleted");
        } catch (NaturalPersonNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/naturalpersons";
    }

}
