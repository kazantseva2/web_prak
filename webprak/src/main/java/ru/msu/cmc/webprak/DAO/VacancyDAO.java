package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.List;


public interface VacancyDAO extends CommonDAO<Vacancy, Long> {
    List<Vacancy> getVacanciesByFilter(FilterVacancy filter);
    @Builder
    @Getter
    class FilterVacancy {
        private String company;
        private String position;
        private Integer salary;
    }

    static FilterVacancy.FilterVacancyBuilder getFilterBuilder() {
        return FilterVacancy.builder();
    }
}
