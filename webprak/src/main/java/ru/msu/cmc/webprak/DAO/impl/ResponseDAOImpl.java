package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.ResponseDAO;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;

import java.util.List;

@Repository
public class ResponseDAOImpl extends CommonDAOImpl<Response, Long> implements ResponseDAO {

    public ResponseDAOImpl(){
        super(Response.class);
    }

    @Override
    public List<Response> getResponsesToSeeker(JobSeeker seeker){
        try (Session session = sessionFactory.openSession()) {
            Query<Response> query = session.createQuery("FROM Response WHERE idSeeker = :js", Response.class)
                    .setParameter("js", seeker);

            return query.getResultList();
        }
    }

    @Override
    public List<Response> getResponsesToVacancy(Vacancy vacancy){
        try (Session session = sessionFactory.openSession()) {
            Query<Response> query = session.createQuery("FROM Response WHERE idVacancy = :vac", Response.class)
                    .setParameter("vac", vacancy);

            return query.getResultList();
        }
    }
}
