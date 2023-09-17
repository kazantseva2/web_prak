package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ResponseDAOTest {

    @Autowired
    private ResponseDAO responseDAO;
    @Autowired
    private VacancyDAO vacancyDAO;
    @Autowired
    private JobSeekerDAO jobSeekerDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testDelete() {
        responseDAO.deleteById(3L);
        Response responseID3 = responseDAO.getById(3L);
        assertNull(responseID3);

        responseDAO.delete(responseDAO.getById(2L));
        Response responseID2 = responseDAO.getById(2L);
        assertNull(responseID2);
    }

    @Test
    void testUpdate() {
        Response updateResponse = responseDAO.getById(1L);
        updateResponse.setIdVacancy(vacancyDAO.getById(6L));
        responseDAO.update(updateResponse);

        Response responseId1 = responseDAO.getById(1L);
        assertEquals(vacancyDAO.getById(6L), responseId1.getIdVacancy());
    }

    @Test
    void testListOfResponses() {
        List<Response> responses1 = responseDAO.getResponsesToSeeker(jobSeekerDAO.getById(1L));
        assertEquals(2, responses1.size());

        List<Response> responses2 = responseDAO.getResponsesToVacancy(vacancyDAO.getById(7L));
        assertEquals(1, responses2.size());
    }

    @BeforeEach
    void beforeEach() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new Response(vacancyDAO.getById(5L), jobSeekerDAO.getById(1L)));
        responseList.add(new Response(vacancyDAO.getById(6L), jobSeekerDAO.getById(1L)));
        responseList.add(new Response(vacancyDAO.getById(7L), jobSeekerDAO.getById(3L)));

        responseDAO.saveCollection(responseList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE response RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE response_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}