package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;

import java.util.List;

public interface PrevJobDAO extends CommonDAO<PrevJob, Long> {
    List<JobSeeker> getSeekersWithCompany(String company);
    List<JobSeeker> getSeekersWithPosition(String position);
    List<JobSeeker> getSeekersWithSalary(int salary);

}
