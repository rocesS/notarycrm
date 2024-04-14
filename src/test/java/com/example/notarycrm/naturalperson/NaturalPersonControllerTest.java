package com.example.notarycrm.naturalperson;

import com.example.notarycrm.NaturalPerson.NaturalPerson;
import com.example.notarycrm.NaturalPerson.NaturalPersonController;
import com.example.notarycrm.NaturalPerson.NaturalPersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


}
