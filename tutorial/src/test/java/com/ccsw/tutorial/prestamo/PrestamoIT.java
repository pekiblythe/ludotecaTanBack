package com.ccsw.tutorial.prestamo;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrestamoIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/prestamo";

    public static final Long EXISTS_PRESTAMO_ID = 1L;
    public static final Long NOT_EXISTS_PRESTAMO_ID = 0L;
    private static final String NOT_EXISTS_TITLE = "NotExists";
    private static final String EXISTS_TITLE = "Blasphemous";
    private static final String NEW_PRESTAMO = "Nuevo pretamo";
    private static final Long NOT_EXISTS_CLIENTS = 0L;
    private static final Long EXISTS_CLIENTS = 3L;

    private static final String TITLE_PARAM = "game_id";
    private static final String CLIENTS_ID_PARAM = "clients_id";
    private static final String DATE_PARAM = "datein";

    private static final int PAGE_SIZE = 5;
    private static final int TOTAL_PRESTAMOS = 9;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<PrestamoDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<PrestamoDto>>() {
    };

    ParameterizedTypeReference<List<PrestamoDto>> responseType = new ParameterizedTypeReference<List<PrestamoDto>>() {
    };

    private String getUrlWithParams() {
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH)
                .queryParam(TITLE_PARAM, "{" + TITLE_PARAM + "}")
                .queryParam(CLIENTS_ID_PARAM, "{" + CLIENTS_ID_PARAM + "}")
                .queryParam(DATE_PARAM, "{" + DATE_PARAM + "}").encode().toUriString();
    }

    @Test
    public void findFirstPageWithFiveSizeWithoutFiltersShouldReturnFirstFiveResults() {

        PrestamoSearchDto searchDto = new PrestamoSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<PrestamoDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST,
                new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_PRESTAMOS, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

}
