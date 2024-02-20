package com.ccsw.tutorial.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

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

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanFilterDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class LoanIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/loan";

    public static final Long EXISTS_LOAN_ID = 2L;
    public static final Long DELETE_LOAN_ID = 6L;
    public static final Long MODIFY_LOAN_ID = 3L;
    public static final Long NOT_EXISTS_LOAN_ID = 0L;

    private static final Long NOT_EXISTS_CLIENT = 0L;
    private static final Long EXISTS_CLIENT = 1L;

    private static final LocalDate EXISTS_DATE = LocalDate.of(2024, 2, 12);
    private static final LocalDate NOT_EXISTS_DATE = LocalDate.of(2001, 2, 12);;

    private static final Long NOT_EXISTS_GAME = 0L;
    private static final Long EXISTS_GAME = 1L;

    private static final int TOTAL_LOAN = 7;
    private static final int PAGE_SIZE = 5;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<ResponsePage<LoanDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<LoanDto>>() {
    };

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LOAN, response.getBody().getTotalElements());
        assertEquals(PAGE_SIZE, response.getBody().getContent().size());
    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int elementsCount = TOTAL_LOAN - PAGE_SIZE;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LOAN, response.getBody().getTotalElements());
        assertEquals(elementsCount, response.getBody().getContent().size());
    }

    @Test
    public void findWithoutFiltersShouldReturnAllLoansInDB() {

        int LOAN_WITH_FILTER = 5;

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(1, PAGE_SIZE));

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOAN_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsClientShouldReturnLoans() {

        int LOANS_WITH_FILTER = 2;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(EXISTS_CLIENT);
        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsDataShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;

        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setFilteredDate(EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsGameShouldReturnLoans() {

        int LOANS_WITH_FILTER = 3;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdGame(EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsClientAndDataShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(EXISTS_CLIENT);
        filtro.setFilteredDate(EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsClientAndGameShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(EXISTS_CLIENT);
        filtro.setIdGame(EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsDataAndGameShouldReturnLoans() {
        int LOANS_WITH_FILTER = 1;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setFilteredDate(EXISTS_DATE);
        filtro.setIdGame(EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findExistsWithAllShouldReturnLoans() {

        int LOANS_WITH_FILTER = 1;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(EXISTS_CLIENT);
        filtro.setFilteredDate(EXISTS_DATE);
        filtro.setIdGame(EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void findNotExistsClientShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(NOT_EXISTS_CLIENT);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());

    }

    @Test
    public void findNotExistsDataShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setFilteredDate(NOT_EXISTS_DATE);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());

    }

    @Test
    public void findNotExistsGameShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdGame(NOT_EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());

    }

    /**
     * all odds are considered
     */
    @Test
    public void findNotExistsAnyShouldReturnEmpty() {

        int LOANS_WITH_FILTER = 0;
        LoanFilterDto filtro = new LoanFilterDto();

        filtro.setIdClient(NOT_EXISTS_CLIENT);
        filtro.setFilteredDate(NOT_EXISTS_DATE);
        filtro.setIdGame(NOT_EXISTS_GAME);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));
        searchDto.setFilteredLoan(filtro);

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(LOANS_WITH_FILTER, response.getBody().getSize());
    }

    @Test
    public void saveWithoutIdShouldCreateNewLoan() {

        int loans = TOTAL_LOAN + 1;
        LoanDto dto = new LoanDto();

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);

        LocalDate init_date = LocalDate.of(2024, 02, 15);
        LocalDate fin_date = LocalDate.of(2024, 02, 16);

        dto.setClient(clientDto);
        dto.setInitDate(init_date);
        dto.setFinDate(fin_date);
        dto.setGame(gameDto);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(loans, response.getBody().getSize());

    }

    @Test
    public void modifyWithExistIdShouldModifyLoan() {

        LoanDto dto = new LoanDto();

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);

        GameDto gameDto = new GameDto();
        gameDto.setId(1L);

        LocalDate init_date = LocalDate.of(2024, 02, 15);
        LocalDate fin_date = LocalDate.of(2024, 02, 16);

        dto.setClient(clientDto);
        dto.setInitDate(init_date);
        dto.setFinDate(fin_date);
        dto.setGame(gameDto);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + MODIFY_LOAN_ID, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        LoanSearchDto searchDto = new LoanSearchDto();
        searchDto.setPageable(new PageableRequest(0, PAGE_SIZE));

        ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.POST, new HttpEntity<>(searchDto), responseTypePage);

        assertNotNull(response);
        assertEquals(TOTAL_LOAN, response.getBody().getSize());

        LoanDto loan = response.getBody().getContent().stream().filter(e -> e.getId().equals(MODIFY_LOAN_ID))
                .findFirst().orElse(null);

        assertNotNull(loan);
        assertEquals(clientDto, loan.getClient());
        assertEquals(gameDto, loan.getGame());
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

        LoanDto dto = new LoanDto();
        ClientDto clientdto = new ClientDto();

        dto.setClient(clientdto);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + NOT_EXISTS_LOAN_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
