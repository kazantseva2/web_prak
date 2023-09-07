package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class JobSeekerDAOTest {

    @Autowired
    private JobSeekerDAO seekerDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetByFilter() {
        List<JobSeeker> seekerListAll = (List<JobSeeker>) seekerDAO.getAll();
        assertEquals(3, seekerListAll.size());

        List<JobSeeker> seekers1 = seekerDAO.getJobSeekersByFilter(new JobSeekerDAO.FilterSeeker(null,null, null, 150000));
        assertEquals(1, seekers1.size());
        assertEquals("Пономарев Даниил Евгеньевич", seekers1.get(0).getFullName());

        List<JobSeeker> seekers2 = seekerDAO.getJobSeekersByFilter(new JobSeekerDAO.FilterSeeker(null,"Яндекс", null, null));
        assertEquals(1, seekers2.size());
        assertEquals("Захарова Марьяна Никитична", seekers2.get(0).getFullName());

        List<JobSeeker> seekers3 = seekerDAO.getJobSeekersByFilter(new JobSeekerDAO.FilterSeeker("Коледж",null, null, null));
        assertEquals(1, seekers3.size());
        assertEquals("Беляева Екатерина Александровна", seekers3.get(0).getFullName());

        List<JobSeeker> seekers4 = seekerDAO.getJobSeekersByFilter(new JobSeekerDAO.FilterSeeker(null,null, "директор", null));
        assertEquals(0, seekers4.size());

        List<JobSeeker> seekers5 = seekerDAO.getJobSeekersByFilter(new JobSeekerDAO.FilterSeeker("ВМК","Яндекс", null, null));
        assertEquals(1, seekers5.size());
        assertEquals("Захарова Марьяна Никитична", seekers5.get(0).getFullName());

    }


}
