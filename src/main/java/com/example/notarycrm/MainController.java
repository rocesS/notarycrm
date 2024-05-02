package com.example.notarycrm;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }


}
