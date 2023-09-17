package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PrevJobDAO;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrevJobDAOImpl extends CommonDAOImpl<PrevJob, Long> implements PrevJobDAO {

    public PrevJobDAOImpl() {
        super(PrevJob.class);
    }

    @Override
    public List<JobSeeker> getSeekersWithCompany(String itCompany) {
        try (Session session = sessionFactory.openSession()) {
            Query<PrevJob> query = session.createQuery("FROM PrevJob WHERE company LIKE :comp", PrevJob.class)
                    .setParameter("comp", likeExpr(itCompany));

            List<JobSeeker> seekerList = new ArrayList<>();
            for (var job: query.getResultList()){
                seekerList.add(job.getIdSeeker());
            }

            return seekerList;
        }
    }

    @Override
    public List<JobSeeker> getSeekersWithPosition(String itPosition) {
        try (Session session = sessionFactory.openSession()) {
            Query<PrevJob> query = session.createQuery("FROM PrevJob WHERE position LIKE :pos", PrevJob.class)
                    .setParameter("pos", likeExpr(itPosition));

            List<JobSeeker> seekerList = new ArrayList<>();
            for (var job: query.getResultList()){
                seekerList.add(job.getIdSeeker());
            }

            return seekerList;
        }
    }

    @Override
    public List<JobSeeker> getSeekersWithSalary(int itSalary) {
        try (Session session = sessionFactory.openSession()) {
            Query<PrevJob> query = session.createQuery("FROM PrevJob WHERE salary = :sal", PrevJob.class)
                    .setParameter("sal", itSalary);

            List<JobSeeker> seekerList = new ArrayList<>();
            for (var job: query.getResultList()){
                seekerList.add(job.getIdSeeker());
            }

            return seekerList;
        }
    }

    @Override
    public List<PrevJob> getJobsToSeeker(JobSeeker seeker){
        try (Session session = sessionFactory.openSession()) {
            Query<PrevJob> query = session.createQuery("FROM PrevJob WHERE idSeeker = :skr", PrevJob.class)
                    .setParameter("skr", seeker);

            return query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}


