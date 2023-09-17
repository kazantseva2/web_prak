package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.DAO.JobSeekerDAO;
import ru.msu.cmc.webprak.DAO.PrevJobDAO;
import ru.msu.cmc.webprak.DAO.impl.CompanyDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.JobSeekerDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.PrevJobDAOImpl;
import ru.msu.cmc.webprak.models.*;

import java.util.List;


@Controller
public class CompanyController {
    @Autowired
    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @Autowired
    private final JobSeekerDAO seekerDAO = new JobSeekerDAOImpl();

    @Autowired
    private final PrevJobDAO prevJobDAO = new PrevJobDAOImpl();

    @RequestMapping(value = { "/companyPage/{companyId}"})
    public String companyPage(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        model.addAttribute("company", company);
        return "companyPage";
    }

    @GetMapping("/companyPage/{companyId}/edit")
    public String companyEditPage(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        model.addAttribute("company", company);
        return "companyEdit";
    }

    @PostMapping("/companyPage/{companyId}/update")
    public String updateCompany(@PathVariable(value = "companyId")  Long companyId,@RequestParam String login,
                               @RequestParam String password, @RequestParam String name,
                               @RequestParam String info, Model model) {
        Company company = companyDAO.getById(companyId);

        if(login != null){
            company.setLogin(login);
        }
        if(password != null){
            company.setPassword(password);
        }
        if(name != null){
            company.setName(name);
        }
        if(info != null){
            company.setInfo(info);
        }

        companyDAO.update(company);

        model.addAttribute("company", company);
        return "companyPage";
    }

    @PostMapping("/companyPage/{companyId}/remove")
    public String removeSeeker(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        companyDAO.delete(company);
        return "indexCompany";
    }

    @GetMapping("/companyPage/{companyId}/seekers")
    public String seekersListPage(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        List<JobSeeker> seekerList =(List<JobSeeker>) seekerDAO.getAll();
        model.addAttribute("company", company);
        model.addAttribute("seekerList", seekerList);
        return "listSeekers";
    }

    @RequestMapping ("/companyPage/{companyId}/filter")
    public String seekerFilterPage(@PathVariable(value = "companyId")  Long companyId,
                                   @RequestParam String education, @RequestParam String company, @RequestParam String position,
                                   @RequestParam String salary,Model model) {
        Company comp = companyDAO.getById(companyId);
        Integer sal = null;
        if(salary != ""){
            sal = Integer.parseInt(salary);
        }
        if(company == ""){
            company=null;
        }
        if(position == ""){
            position=null;
        }
        if(education == ""){
            education=null;
        }
        List<JobSeeker> seekerList =seekerDAO.getJobSeekersByFilter(education, company, position, sal);
        model.addAttribute("seekerList", seekerList);
        model.addAttribute("company", comp);
        return "listSeekers";
    }

    @GetMapping("/companyPage/{companyId}/seeker/{seekerId}")
    public String seekersListPage(@PathVariable(value = "companyId")  Long companyId,
                                  @PathVariable(value = "seekerId")  Long seekerId, Model model) {
        Company company = companyDAO.getById(companyId);
        JobSeeker seeker = seekerDAO.getById(seekerId);
        List<PrevJob> jobList = prevJobDAO.getJobsToSeeker(seeker);
        model.addAttribute("company", company);
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobList", jobList);
        return "infoSeeker";
    }
}