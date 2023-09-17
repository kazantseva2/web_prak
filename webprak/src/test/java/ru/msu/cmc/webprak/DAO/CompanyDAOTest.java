package ru.msu.cmc.webprak.DAO;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Company;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class CompanyDAOTest {

    @Autowired
    private CompanyDAO companyDAO;

    @Test
    void testLogin() {
        Company company = companyDAO.getUser( "83nJ0JSZ09","83ndnoen-lapqnx90");
        assertEquals("ПАО \"МегаФон\"", company.getName());
    }
}
