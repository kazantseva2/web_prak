package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.List;


public interface ResponseDAO extends CommonDAO<Response, Long> {

    List<Response> getResponsesToSeeker(JobSeeker seeker);
    List<Response> getResponsesToVacancy(Vacancy vacancy);
}
