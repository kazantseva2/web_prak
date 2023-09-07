package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.List;


public interface ResponseDAO extends CommonDAO<Response, Long> {

    List<Vacancy> getResponsesToSeeker(JobSeeker seeker);
    List<JobSeeker> getResponsesToVacancy(Vacancy vacancy);
}
