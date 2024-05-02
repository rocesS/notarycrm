package com.example.notarycrm.legalperson;

import com.example.notarycrm.LegalPerson.KrsValidationException;
import com.example.notarycrm.LegalPerson.KrsValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KrsValidatorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private KrsValidatorService krsValidatorService;

    @Test
    public void validateKrsNumber_Success() throws KrsValidationException {
        String krsNumber = "1234567890";
        ResponseEntity<String> mockedResponse = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockedResponse);  //tzw stubbing (izolacja klasy, wywolanie metody i podanie co ma zwrocic)

        assertTrue(krsValidatorService.validateKrsNumber(krsNumber));
    }

    @Test
    public void validateKrsNumber_Failure() throws KrsValidationException {
        String krsNumber = "1234567890";
        ResponseEntity<String> mockedResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockedResponse);

        assertThrows(KrsValidationException.class, () ->krsValidatorService.validateKrsNumber(krsNumber));
    }

    @Test
    public void validateKrsNumber_ErrorInRestClient() throws KrsValidationException {
        String krsNumber = "1234567890";
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new RestClientException("Connection failed"));

        assertThrows(KrsValidationException.class, () ->krsValidatorService.validateKrsNumber(krsNumber));
    }

}
