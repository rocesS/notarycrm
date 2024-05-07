package com.example.notarycrm.legalperson;

import com.example.notarycrm.controller.LegalPersonController;
import com.example.notarycrm.model.LegalPerson;
import com.example.notarycrm.service.LegalPersonService;
import com.example.notarycrm.service.exceptions.KrsValidationException;
import com.example.notarycrm.service.exceptions.LegalPersonNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LegalPersonController.class)
@AutoConfigureMockMvc

public class LegalPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LegalPersonService legalPersonService;

    @Test
    public void showLegalPersonList() throws Exception {
        List<LegalPerson> listLegalPersons = Arrays.asList(new LegalPerson(), new LegalPerson());
        when(legalPersonService.legalPersonList()).thenReturn(listLegalPersons);

        mockMvc.perform(get("/legalpersons"))
                .andExpect(status().isOk())
                .andExpect(view().name("legalpersons"))
                .andExpect(model().attribute("listLegalPersons", listLegalPersons));

    }

    @Test
    public void showNewForm() throws Exception {
        mockMvc.perform(get("/legalpersons/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("legalperson_form"))
                .andExpect(model().attributeExists("legalperson"))
                .andExpect(model().attributeExists("pageTitle"));
    }

    @Test
    public void saveLegalPerson() throws Exception, KrsValidationException {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setId(1);
        legalPerson.setName("ADC Group Sp. z o. o.");
        legalPerson.setEmail("email.napisz@domnie.pl");
        legalPerson.setPhoneNumber("+48 504543234");
        legalPerson.setAddress("ul. Ruska 37/38, 50-079 Wrocław");
        legalPerson.setKrsNumber("0000435013");

        doNothing().when(legalPersonService).save(legalPerson);

        mockMvc.perform(post("/legalpersons/save")
                .param("id","1")
                .param("name", "ADC Group Sp. z o. o.")
                .param("email", "email.napisz@domnie.pl")
                .param("phoneNumber", "+48 504543234")
                .param("address", "ul. Ruska 37/38, 50-079")
                .param("krsNumber", "0000435013"));
    }

    @Test
    public void showEditForm() throws Exception, LegalPersonNotFoundException {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setId(1);
        legalPerson.setName("ADC Group Sp. z o. o.");
        legalPerson.setEmail("email.napisz@domnie.pl");
        legalPerson.setPhoneNumber("+48 504543234");
        legalPerson.setAddress("ul. Ruska 37/38, 50-079 Wrocław");

        when(legalPersonService.get(1)).thenReturn(legalPerson);

        mockMvc.perform(get("/legalpersons/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("legalperson_form"))
                .andExpect(model().attribute("legalperson", legalPerson))
                .andExpect(model().attribute("pageTitle", "Edit Legal Person (ID: 1)"));
    }

    @Test
    public void deleteLegalPerson_Success() throws Exception, LegalPersonNotFoundException {
        Integer legalPersonId = 1;
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setId(legalPersonId);
        legalPerson.setName("ADC Group Sp. z o. o.");
        legalPerson.setEmail("email.napisz@domnie.pl");

        when(legalPersonService.get(legalPersonId)).thenReturn(legalPerson);
        doNothing().when(legalPersonService).delete(legalPersonId);

        mockMvc.perform(get("/legalpersons/delete/{id}", legalPersonId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/legalpersons"))
                .andExpect(flash().attribute("message", legalPerson.getName() + " has been deleted"));

        verify(legalPersonService, times(1)).delete(legalPersonId);
        verify(legalPersonService, times(1)).delete(legalPersonId);
    }

    @Test
    public void deleteLegalPerson_NotFound() throws Exception, LegalPersonNotFoundException {
        Integer legalPersonId = 1;
        //przygotowanie mocka
        when(legalPersonService.get(legalPersonId)).thenThrow(new LegalPersonNotFoundException("Legal Person not found"));

        //wykonanie zadania
        mockMvc.perform(get("/legalpersons/delete/{id}", legalPersonId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/legalpersons"))
                .andExpect(flash().attribute("message", "Legal Person not found"));

        verify(legalPersonService, times(1)).get(legalPersonId); //spr wywolasnie raz z argumentem 1
        verify(legalPersonService, never()).delete(legalPersonId); //spr czy operacja nie zostala wykonana
    }

}
