package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.JobSeekerDAO;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.DAO.PrevJobDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobSeekerDAOImpl extends CommonDAOImpl<JobSeeker, Long> implements JobSeekerDAO {

    @Autowired
    private PrevJobDAO prevJobDAO = new PrevJobDAOImpl();

    public JobSeekerDAOImpl(){
        super(JobSeeker.class);
    }

    @Override
    public JobSeeker getUser(String login, String password){
        List<JobSeeker> userList = getLoginList(login);
        for (JobSeeker seeker : userList) {
            if(password.equals(seeker.getPassword())){
                return seeker;
            }
        }
        return null;
    }

    private List<JobSeeker> getLoginList(String enterLogin) {
        try (Session session = sessionFactory.openSession()) {
            Query<JobSeeker> query = session.createQuery("FROM JobSeeker WHERE login LIKE :log", JobSeeker.class)
                    .setParameter("log", enterLogin);

            return query.getResultList();
        }
    }
    private List<JobSeeker> getSeekersWithEducation(String education) {
        try (Session session = sessionFactory.openSession()) {
            Query<JobSeeker> query = session.createQuery("FROM JobSeeker WHERE educationInfo LIKE :educat", JobSeeker.class)
                    .setParameter("educat", likeExpr(education));

            return query.getResultList();
        }
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
    public List<JobSeeker> getJobSeekersByFilter(String education, String company, String position, Integer salary) {

        List<JobSeeker> seekers = null;

        //Если все параметры не заданы, выдаем список всех анкет
        if(education == null &&  company == null && position == null && salary == null){
            return (List<JobSeeker>) this.getAll();
        }
        //Если указано образование
        if(education != null){
            seekers = getSeekersWithEducation(education);
        }

        //Если указана компания
        if(company != null){
            List<JobSeeker> skList = prevJobDAO.getSeekersWithCompany(company);
            if(seekers == null){
                seekers = skList;
            } else {
                seekers = intersection(seekers, skList);
            }
        }

        //Если указана должность
        if(position != null){
            List<JobSeeker> skList = prevJobDAO.getSeekersWithPosition(position);
            if(seekers == null){
                seekers = skList;
            } else {
                seekers = intersection(seekers, skList);
            }
        }

        //Если указана зарплата
        if(salary != null){
            List<JobSeeker> skList = prevJobDAO.getSeekersWithSalary(salary);
            if(seekers == null){
                seekers = skList;
            } else {
                seekers = intersection(seekers, skList);
            }
        }

        return seekers;
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }

}
