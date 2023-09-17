package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.JobSeeker;
import java.util.List;

public interface JobSeekerDAO extends CommonDAO<JobSeeker, Long> {

    List<JobSeeker> getJobSeekersByFilter(String education, String company, String position, Integer salary);

    JobSeeker getUser(String login, String password);

}
