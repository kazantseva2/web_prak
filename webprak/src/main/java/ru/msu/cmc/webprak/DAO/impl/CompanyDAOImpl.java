package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.models.Company;

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

    @Override
    public Company getUser(String login, String password){
        List<Company> userList = getLoginList(login);
        for (Company company : userList) {
            if(password.equals(company.getPassword())){
                return company;
            }
        }
        return null;
    }
    private List<Company> getLoginList(String enterLogin) {
        try (Session session = sessionFactory.openSession()) {
            Query<Company> query = session.createQuery("FROM Company WHERE login LIKE :log", Company.class)
                    .setParameter("log", enterLogin);

            return query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
