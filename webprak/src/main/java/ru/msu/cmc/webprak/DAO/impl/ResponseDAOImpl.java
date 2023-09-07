package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.ResponseDAO;
import ru.msu.cmc.webprak.models.Company;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponseDAOImpl extends CommonDAOImpl<Response, Long> implements ResponseDAO {

    public ResponseDAOImpl(){
        super(Response.class);
    }

    @Override
    public List<Vacancy> getResponsesToSeeker(JobSeeker seeker){
        try (Session session = sessionFactory.openSession()) {
            Query<Response> query = session.createQuery("FROM Response WHERE idSeeker = :js", Response.class)
                    .setParameter("js", seeker);

            return listResponseToVacancy(query.getResultList());
        }
    }

    private List<Vacancy> listResponseToVacancy(List<Response> responses){
        List<Vacancy> vacancies = new ArrayList<>();
        for (Response response : responses) {
            vacancies.add(response.getIdVacancy());
        }
        return vacancies;
    }

    @Override
    public List<JobSeeker> getResponsesToVacancy(Vacancy vacancy){
        try (Session session = sessionFactory.openSession()) {
            Query<Response> query = session.createQuery("FROM Response WHERE idVacancy = :vac", Response.class)
                    .setParameter("vac", vacancy);

            return listResponseToSeeker(query.getResultList());
        }
    }

    private List<JobSeeker> listResponseToSeeker(List<Response> responses){
        List<JobSeeker> seekers = new ArrayList<>();
        for (Response response : responses) {
            seekers.add(response.getIdSeeker());
        }
        return seekers;
    }
}
