package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Company;
import java.util.List;

public interface CompanyDAO extends CommonDAO<Company, Long> {

    List<Company> getCompany(String company);
    Company getUser(String login, String password);
}
