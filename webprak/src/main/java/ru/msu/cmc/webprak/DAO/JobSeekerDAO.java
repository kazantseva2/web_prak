package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.DAO.impl.JobSeekerDAOImpl;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;

import java.util.List;

public interface JobSeekerDAO extends CommonDAO<JobSeeker, Long> {

    List<JobSeeker> getJobSeekersByFilter(FilterSeeker filter);
    @Builder
    @Getter
    class FilterSeeker {
        private String education;
        private String company;
        private String position;
        private Integer salary;
    }

    static FilterSeeker.FilterSeekerBuilder getFilterBuilder() {
        return FilterSeeker.builder();
    }

}
