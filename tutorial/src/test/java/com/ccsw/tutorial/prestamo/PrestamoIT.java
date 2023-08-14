package com.ccsw.tutorial.prestamo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.clients.model.ClientsDto;
import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    private static final Long BLASPH_ID = 8L;
    private static final Long PAULA_ID = 1L;
    private static final Long PILAR_ID = 4L;

    private static final Long NOT_EXISTS_TITLE = 0L;
    private static final Long NOT_EXISTS_CLIENTS = 0L;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final int PAGE_SIZE = 5;
    private static final int PAGE_SIZE_20 = 20;
    private static final int TOTAL_PRESTAMOS = 9;
    private static final int TOTAL_PRESTAMOS_10 = 10;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<PrestamoDto>>() {
    };

    ParameterizedTypeReference<List<PrestamoDto>> responseType = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    @Test
    public void findWithoutFiltersShouldReturnAllPrestamoInDB() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findExistsTitleShouldReturnPrestamo() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setGameId(BLASPH_ID);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void findExistsClientsShouldReturnPrestamo() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setClientsId(PAULA_ID);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(4, response.getBody().getContent().size());
    }

    @Test
    public void findExistsTitleAndClientsShouldReturnPrestamo() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setGameId(BLASPH_ID);
        searchDto.setClientsId(PILAR_ID);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsTitleShouldReturnEmpty() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setGameId(NOT_EXISTS_TITLE);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getContent().size());
    }

    @Test
    public void findNotExistsClientsShouldReturnEmpty() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setClientsId(NOT_EXISTS_CLIENTS);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(0, response.getBody().getContent().size());
    }

    @Test
    public void saveWithoutIdShouldCreateNewPrestamo() {

        GameDto game = new GameDto();
        PrestamoDto dto = new PrestamoDto();
        ClientsDto clients = new ClientsDto();

        game.setId(BLASPH_ID);
        clients.setId(PAULA_ID);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE_20));

        dto.setClients(clients);
        dto.setGame(game);
        dto.setDatein(LocalDate.parse("2023-12-12"));
        dto.setDateout(LocalDate.parse("2023-12-14"));

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS_10, response.getBody().getContent().size());
    }

    @Test
    public void saveWithExistingPrestamoShouldNotCreateNewPrestamo() {

        GameDto game = new GameDto();
        PrestamoDto dto = new PrestamoDto();
        ClientsDto clients = new ClientsDto();

        game.setId(BLASPH_ID);
        clients.setId(PILAR_ID);

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE_20));

        dto.setClients(clients);
        dto.setGame(game);
        dto.setDatein(LocalDate.parse("2023-09-04"));
        dto.setDateout(LocalDate.parse("2023-09-08"));

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getContent().size());
    }

}
