package com.example.notarycrm.LegalPerson;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KrsValidatorService {

    private final String API_URL = "https://api-krs.ms.gov.pl/api/krs/OdpisAktualny/{krs}?rejestr=P&format=json";

    public boolean validateKrsNumber(String krsNumber) {
        //obiekt do komunikacji z zew API https://prs.ms.gov.pl/krs/openApi
        RestTemplate restTemplate = new RestTemplate();
        //podmiana krs od użytkownika do zapytania
        String url = API_URL.replace("{krs}", krsNumber);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            HttpStatusCode statusCode = response.getStatusCode();

            if(statusCode == HttpStatus.OK)
                return true;
            else {
                System.err.println("Checking KRS number failed. Status: " + statusCode);
            }
        } catch (RestClientException e) {
            System.err.println("Error during connection with KRS service" + e.getMessage());
            return false;
        }
        return false;
    }
}
