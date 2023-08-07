package com.ccsw.tutorial.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.clients.model.ClientsDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClientsIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/clients";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<ClientsDto>> responseType = new ParameterizedTypeReference<List<ClientsDto>>() {
    };

    @Test
    public void findAllShouldReturnAllClients() {

        ResponseEntity<List<ClientsDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(4, response.getBody().size());
    }

    public static final Long NEW_CLIENTS_ID = 5L;
    public static final String NEW_CLIENTS_NAME = "CLI5";

    @Test
    public void saveWithoutIdShouldCreateNewClients() {

        ClientsDto dto = new ClientsDto();
        dto.setName(NEW_CLIENTS_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<ClientsDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(5, response.getBody().size());

        ClientsDto clientsSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CLIENTS_ID))
                .findFirst().orElse(null);
        assertNotNull(clientsSearch);
        assertEquals(NEW_CLIENTS_NAME, clientsSearch.getName());
    }

    public static final Long MODIFY_CLIENTS_ID = 3L;

    @Test
    public void modifyWithExistIdShouldModifyClients() {

        ClientsDto dto = new ClientsDto();
        dto.setName(NEW_CLIENTS_NAME);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_CLIENTS_ID, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<ClientsDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(4, response.getBody().size());

        ClientsDto clientsSearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CLIENTS_ID))
                .findFirst().orElse(null);
        assertNotNull(clientsSearch);
        assertEquals(NEW_CLIENTS_NAME, clientsSearch.getName());
    }

    @Test
    public void modifyWithNotExistIdShouldInternalError() {

        ClientsDto dto = new ClientsDto();
        dto.setName(NEW_CLIENTS_NAME);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CLIENTS_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    public static final Long DELETE_CLIENTS_ID = 2L;

    @Test
    public void deleteWithExistsIdShouldDeleteClients() {

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + DELETE_CLIENTS_ID, HttpMethod.DELETE, null,
                Void.class);

        ResponseEntity<List<ClientsDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response);
        assertEquals(4, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NEW_CLIENTS_ID,
                HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}