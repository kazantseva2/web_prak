package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.models.Company;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyDAOImpl extends CommonDAOImpl<Company, Long> implements CompanyDAO {

    public CompanyDAOImpl(){
        super(Company.class);
    }

    @Override
    public List<Company> getCompany(String company) {
        try (Session session = sessionFactory.openSession()) {
            Query<Company> query = session.createQuery("FROM Company WHERE name LIKE :com", Company.class)
                    .setParameter("com", likeExpr(company));

            return query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
