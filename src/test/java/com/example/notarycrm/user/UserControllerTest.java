package com.example.notarycrm.user;

import com.example.notarycrm.User.User;
import com.example.notarycrm.User.UserController;
import com.example.notarycrm.User.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) //integrowanie testu z kontekstem springa
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testShowUserList() throws Exception {
        when(userService.userList()).thenReturn(Arrays.asList(new User()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("listUsers"));
    }

    @Test
    public void testShowNewForm() throws Exception {
        mockMvc.perform(get("/users/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user_form"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("pageTitle"));
    }

    @Test
    public void testSaveUser() throws Exception {
        // Tworzymy przykładowego użytkownika do zapisania
        User user = new User();
        user.setId(1);
        user.setLogin("testuser");
        user.setPassword("testpassword");
        user.setEmail("test@example.com");

        // symulacja wykorzystania serwisu
        doNothing().when(userService).save(user);

        // Wywołujemy metodę saveUser kontrolera za pomocą MockMvc
        mockMvc.perform(post("/users/save")
                .param("login", "testuser")
                .param("password", "testpassword")
                .param("email", "test@example.com")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection()) // przekierowanie kod "3xx"
                .andExpect(redirectedUrl("/users")) // Oczekujemy przekierowania na /users
                .andExpect(flash().attributeExists("message")); // Oczekujemy atrybutu flash z wiadomością
    }


}