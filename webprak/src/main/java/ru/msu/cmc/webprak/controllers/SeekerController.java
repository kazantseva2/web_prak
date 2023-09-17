package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.JobSeekerDAO;
import ru.msu.cmc.webprak.DAO.PrevJobDAO;
import ru.msu.cmc.webprak.DAO.ResponseDAO;
import ru.msu.cmc.webprak.DAO.impl.JobSeekerDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.PrevJobDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.ResponseDAOImpl;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.PrevJob;
import ru.msu.cmc.webprak.models.Response;

import java.util.List;

@Controller
public class SeekerController {
    @Autowired
    private final JobSeekerDAO seekerDAO = new JobSeekerDAOImpl();

    @Autowired
    private final ResponseDAO responseDAO = new ResponseDAOImpl();

    @Autowired
    private final PrevJobDAO prevJobDAO = new PrevJobDAOImpl();

    @RequestMapping(value = { "/seekerLogin"})
    public String seekerLoginPage() {
        return "seekerLogin";
    }

    @PostMapping("/seekerLogin")
    public String seekerLogin(@RequestParam String login, @RequestParam String password, Model model) {
        JobSeeker seeker = seekerDAO.getUser(login,password);
        if (seeker == null) {
            model.addAttribute("error_msg", "Некорректный логин или пароль");
            return "errorPage";
        }
        model.addAttribute("seeker", seeker);
        return "seekerPage";
    }

    @RequestMapping(value = { "/seekerPage/{seekerId}"})
    public String seekerPage(@PathVariable(value = "seekerId")  Long seekerId, Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        model.addAttribute("seeker", seeker);
        return "seekerPage";
    }

    @GetMapping("/seekerPage/{seekerId}/edit")
    public String seekerEditPage(@PathVariable(value = "seekerId")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        model.addAttribute("seeker", seeker);
        return "seekerEdit";
    }

    @GetMapping("/seekerPage/{seekerId}/prevJob")
    public String seekerPrevJobPage(@PathVariable(value = "seekerId")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        List<PrevJob> jobList = prevJobDAO.getJobsToSeeker(seeker);
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobList", jobList);
        return "seekerPrevJob";
    }

    @GetMapping("/seekerPage/{seekerId}/prevJob/add")
    public String seekerJobAddPage(@PathVariable(value = "seekerId")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        model.addAttribute("seeker", seeker);
        return "seekerJobAdd";
    }

    @PostMapping("/seekerPage/{seekerId}/prevJob/add")
    public String seekerJobAdd(@RequestParam String company, @RequestParam String position,
                               @RequestParam String salary, @PathVariable(value = "seekerId")  Long id,
                               Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        PrevJob job = new PrevJob(seeker);

        if(company != null){
            job.setCompany(company);
        }
        if(position != null){
            job.setPosition(position);
        }
        if(salary != null){
            Integer number = Integer.parseInt(salary);
            job.setSalary(number);
        }
        prevJobDAO.save(job);

        List<PrevJob> jobList = prevJobDAO.getJobsToSeeker(seeker);
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobList", jobList);
        return "seekerPrevJob";
    }

    @RequestMapping("/seekerPage/{seekerId}/prevJob/{jobId}")
    public String seekerJobDelete(@PathVariable(value = "seekerId")  Long seekerId,
                                  @PathVariable(value = "jobId")  Long jobId,
                                  Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        prevJobDAO.deleteById(jobId);
        List<PrevJob> jobList = prevJobDAO.getJobsToSeeker(seeker);
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobList", jobList);
        return "seekerPrevJob";
    }

    @GetMapping("/seekerPage/{seekerId}/response")
    public String seekerResponsesPage(@PathVariable(value = "seekerId")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        List<Response> responseList = responseDAO.getResponsesToSeeker(seeker);
        model.addAttribute("seeker", seeker);
        model.addAttribute("responseList", responseList);
        return "seekerResponses";
    }

    @RequestMapping("/seekerPage/{seekerId}/response/{responseId}")
    public String seekerResponsesDeletePage(@PathVariable(value = "seekerId")  Long seekerId,
                                      @PathVariable(value = "responseId")  Long responseId,
                                      Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        responseDAO.deleteById(responseId);
        List<Response> responseList = responseDAO.getResponsesToSeeker(seeker);
        model.addAttribute("seeker", seeker);
        model.addAttribute("responseList", responseList);
        return "seekerResponses";
    }

    @PostMapping("/seekerPage/{seekerId}/update")
    public String updateSeeker(@PathVariable(value = "seekerId")  Long id,@RequestParam String login,
                               @RequestParam String password, @RequestParam String fullName,
                               @RequestParam String educationInfo, @RequestParam String status,
                               @RequestParam String desiredPosition,@RequestParam String desiredSalary,
                               @RequestParam String contactInfo, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);

        if(login != null){
            seeker.setLogin(login);
        }

        if(password != null){
            seeker.setPassword(password);
        }

        if(fullName != null){
            seeker.setFullName(fullName);
        }

        if(educationInfo != null){
            seeker.setEducationInfo(educationInfo);
        }

        if(status != null){
            seeker.setStatus(status);
        }

        if(desiredPosition != null){
            seeker.setDesiredPosition(desiredPosition);
        }

        if(desiredSalary != null){
            Integer number = Integer.parseInt(desiredSalary);
            seeker.setDesiredSalary(number);
        }

        if(contactInfo != null){
            seeker.setContactInfo(contactInfo);
        }
        seekerDAO.update(seeker);

        model.addAttribute("seeker", seeker);
        return "seekerPage";
    }

    @PostMapping("/seekerPage/{seekerId}/remove")
    public String removeSeeker(@PathVariable(value = "seekerId")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(id);
        seekerDAO.delete(seeker);
        return "index";
    }
}
