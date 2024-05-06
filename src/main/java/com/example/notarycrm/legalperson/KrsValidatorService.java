package com.example.notarycrm.legalperson;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode; // Import the HttpStatusCode if it's the expected type
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KrsValidatorService {

    private static final String API_URL = "https://api-krs.ms.gov.pl/api/krs/OdpisAktualny/{krs}?rejestr=P&format=json";
    private final RestTemplate restTemplate;

    public KrsValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateKrsNumber(String krsNumber) throws KrsValidationException {
        String url = API_URL.replace("{krs}", krsNumber);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            HttpStatusCode statusCode = response.getStatusCode(); // Change the type to HttpStatusCode if that's what getStatusCode() returns

            if(statusCode.is2xxSuccessful()) { // dla odpowiedzi z grupy 200
                return true;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new KrsValidationException("Provided KRS number does not exist.");
            } else {
                throw new KrsValidationException("Checking KRS number failed. Status: " + statusCode);
            }
        } catch (RestClientException e) {
            throw new KrsValidationException("KRS number is not valid. Check KRS number and try again.");
        }
    }
}