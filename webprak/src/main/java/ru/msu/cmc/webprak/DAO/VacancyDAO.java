package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Vacancy;
import java.util.List;


public interface VacancyDAO extends CommonDAO<Vacancy, Long> {
    List<Vacancy> getVacanciesByFilter(String company, String position, Integer salary);

}
