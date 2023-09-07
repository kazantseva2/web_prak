package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class VacancyDAOTest {

    @Autowired
    private VacancyDAO vacancyDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulations() {
        List<Vacancy> vacancyListAll = (List<Vacancy>) vacancyDAO.getAll();
        assertEquals(3, vacancyListAll.size());

        List<Vacancy> vacancies1 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy(null, "Senior", 200000));
        assertEquals(1, vacancies1.size());
        assertEquals("Разработчик веб-приложений", vacancies1.get(0).getTitle());

        List<Vacancy> vacancies5 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy("Яндекс", "Senior", 200000));
        assertEquals(0, vacancies5.size());

        List<Vacancy> vacancies6 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy("Яндекс", null, null));
        assertEquals(0, vacancies6.size());

        List<Vacancy> vacancies2 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy("МегаФон", null, 40000));
        assertEquals(1, vacancies2.size());
        assertEquals("Грузчик, полный рабочий день.", vacancies2.get(0).getTitle());

        List<Vacancy> vacancies3 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy("ВКонтакте","Junior", null));
        assertEquals(1, vacancies3.size());

        List<Vacancy> vacancies4 = vacancyDAO.getVacanciesByFilter(new VacancyDAO.FilterVacancy(null,null, null));
        assertEquals(3, vacancies4.size());
    }
}
