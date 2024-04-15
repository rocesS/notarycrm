package com.example.notarycrm.naturalperson;

import com.example.notarycrm.NaturalPerson.NaturalPerson;
import com.example.notarycrm.NaturalPerson.NaturalPersonController;
import com.example.notarycrm.NaturalPerson.NaturalPersonNotFoundException;
import com.example.notarycrm.NaturalPerson.NaturalPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NaturalPersonController.class)
@AutoConfigureMockMvc

public class NaturalPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NaturalPersonService naturalPersonService;

    @InjectMocks
    private NaturalPersonController naturalPersonController;

    @Test
    public void testShowNaturalPersonList() throws Exception {
        List<NaturalPerson> naturalPersonList = Arrays.asList(new NaturalPerson(), new NaturalPerson());
        when(naturalPersonService.naturalPersonList()).thenReturn(naturalPersonList);

        mockMvc.perform(get("/naturalpersons"))
                .andExpect(status().isOk()) //status 200 dla zapytania get
                .andExpect(view().name("naturalpersons"))
                .andExpect(model().attribute("naturalPersonsList", naturalPersonList));
    }

    @Test
    public void testShowNewForm () throws Exception {
        mockMvc.perform(get("/naturalpersons/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("naturalperson_form"))
                .andExpect(model().attributeExists("naturalPerson"))
                .andExpect(model().attributeExists("pageTitle"));
    }

    @Test
    public void testSaveNaturalPerson() throws Exception {
        NaturalPerson naturalPerson = new NaturalPerson();
        naturalPerson.setId(1);
        naturalPerson.setFirstName("Maniek");
        naturalPerson.setLastName("Bodnar");
        naturalPerson.setPesel("10108712134");
        naturalPerson.setNationality("niemieckie");

        doNothing().when(naturalPersonService).save(naturalPerson);

        mockMvc.perform(post("/naturalpersons/save")
                .param("id","1")
                .param("firstName", "Maniek")
                .param("lastName","Bodnar")
                .param("pesel","10108712134")
                .param("nationality","niemieckie")
                .flashAttr("naturalPerson", naturalPerson))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/naturalpersons"))
                .andExpect(flash().attributeExists("message"));
    }

    @Test
    public void testShowEditForm() throws NaturalPersonNotFoundException {
        Integer naturalPersonId = 1;
        NaturalPerson naturalPerson = new NaturalPerson();
        naturalPerson.setId(naturalPersonId);
        naturalPerson.setFirstName("Maniek");
        naturalPerson.setLastName("Bodnar");
        naturalPerson.setPesel("10108712134");
        naturalPerson.setNationality("niemieckie");

        Model model = mock(Model.class);
        RedirectAttributes ra = mock(RedirectAttributes.class);

        when(naturalPersonService.get(naturalPersonId)).thenReturn(naturalPerson);
        String viewName = naturalPersonController.showEditForm(naturalPersonId, model, ra);
        assertEquals("naturalperson_form", viewName);
        verify(naturalPersonService, times(1)).get(naturalPersonId);
        verify(model,times(1)).addAttribute("naturalPerson",naturalPerson);
        verify(model, times(1)).addAttribute("pageTitle","Edit Natural Person (ID: " + naturalPersonId + ")");
    }
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(naturalPersonController).build();
    }


//    @Test
//    public void testDeleteNaturalPerson() throws NaturalPersonNotFoundException {
//        Integer naturalPersonId = 1;
//        RedirectAttributes ra = mock(RedirectAttributes.class);
//        String viewName = naturalPersonController.deleteUser(naturalPersonId, ra);
//
//        assertEquals("redirect:/narturalpersons", viewName);
//
//        verify(naturalPersonService, times(1)).delete(naturalPersonId);
//
//        verify(ra, times(1)).addFlashAttribute("message", naturalPerson);
//    }

}
