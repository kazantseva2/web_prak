package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.DAO.VacancyDAO;
import ru.msu.cmc.webprak.models.Vacancy;


import java.util.ArrayList;
import java.util.List;

@Repository
public class VacancyDAOImpl extends CommonDAOImpl<Vacancy, Long> implements VacancyDAO {

    @Autowired
    private CompanyDAO companyDAO = new CompanyDAOImpl();

    public VacancyDAOImpl(){
        super(Vacancy.class);
    }

    private List<Vacancy> getWithCompany(String company){
        List<Vacancy> list = new ArrayList<>();
        for(var cmp: companyDAO.getCompany(company)){
            for(var vac: getAll()){
                if(vac.getIdCompany().equals(cmp)){
                    list.add(vac);
                }
            }
        }
        return list;
    }

    private List<Vacancy> getWithPosition(String position) {
        try (Session session = sessionFactory.openSession()) {
            Query<Vacancy> query = session.createQuery("FROM Vacancy WHERE position LIKE :pos", Vacancy.class)
                    .setParameter("pos", likeExpr(position));

            return query.getResultList();
        }
    }

    private List<Vacancy> getWithSalary(int salary) {
        try (Session session = sessionFactory.openSession()) {
            Query<Vacancy> query = session.createQuery("FROM Vacancy WHERE salary > :sal", Vacancy.class)
                    .setParameter("sal", salary);

            return query.getResultList();
        }
    }

   private List<Vacancy> getWithCompanyPositionSalary(String company, String position, int salary){
        return intersection(getWithCompanyPosition(company, position), getWithSalary(salary));
   }

    private List<Vacancy> getWithCompanyPosition(String company, String position){
        List<Vacancy> list = new ArrayList<>();
        for(var cmp: companyDAO.getCompany(company)){
            for(var pst: getWithPosition(position)){
                if(pst.getIdCompany().equals(cmp)){
                    list.add(pst);
                }
            }
        }
        return list;
    }

    private List<Vacancy> getWithPositionSalary(String position, int salary){
        return intersection(getWithPosition(position), getWithSalary(salary));
    }

    private List<Vacancy> getWithCompanySalary(String company, int salary){
        List<Vacancy> list = new ArrayList<>();
        for(var cmp: companyDAO.getCompany(company)){
            for(var pst: getWithSalary(salary)){
                if(pst.getIdCompany().equals(cmp)){
                    list.add(pst);
                }
            }
        }
        return list;
    }

    private  <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();
        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public List<Vacancy> getVacanciesByFilter(FilterVacancy filter) {
        String company = filter.getCompany();
        String position = filter.getPosition();
        Integer salary = filter.getSalary();

        // Если ни один параметр не является null, то выполняем прямой запрос
        if (company != null && position != null && salary != null) {
            return getWithCompanyPositionSalary(company, position, salary);
        }

        // Если один или несколько параметров являются null, то выполняем более сложные запросы

        // Если указана компания
        if (company != null) {
            if (position != null) {
                return getWithCompanyPosition(company, position);
            } else if (salary != null) {
                return getWithCompanySalary(company, salary);
            } else {
                return getWithCompany(company);
            }
        }

        // Если указана позиция
        if (position != null) {
            if (salary != null) {
                return getWithPositionSalary(position, salary);
            } else {
                return getWithPosition(position);
            }
        }

        // Если указана зарплата
        if (salary != null) {
            return getWithSalary(salary);
        }

        // Если ни один параметр не указан, то возвращаем все вакансии
        return (List<Vacancy>) this.getAll();
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
