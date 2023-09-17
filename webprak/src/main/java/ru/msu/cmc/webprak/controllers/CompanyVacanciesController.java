package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.*;
import ru.msu.cmc.webprak.DAO.impl.*;
import ru.msu.cmc.webprak.models.*;

import java.util.List;


@Controller
public class CompanyVacanciesController {

    @Autowired
    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @Autowired
    private final VacancyDAO vacancyDAO = new VacancyDAOImpl();

    @Autowired
    private final ResponseDAO responseDAO = new ResponseDAOImpl();

    @Autowired
    private final JobSeekerDAO seekerDAO = new JobSeekerDAOImpl();

    @Autowired
    private final PrevJobDAO prevJobDAO = new PrevJobDAOImpl();

    @GetMapping("/companyPage/{companyId}/vacancies")
    public String companyEditPage(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        List<Vacancy> vacancyList = vacancyDAO.getVacanciesByFilter(company.getName(),null, null);
        model.addAttribute("company", company);
        model.addAttribute("vacancyList", vacancyList);
        return "companyVacancies";
    }

    @GetMapping("/companyPage/{companyId}/vacancies/add")
    public String companyVacancyAddPage(@PathVariable(value = "companyId")  Long companyId, Model model) {
        Company company = companyDAO.getById(companyId);
        model.addAttribute("company", company);
        return "companyVacanciesAdd";
    }

    @PostMapping("/companyPage/{companyId}/vacancies/add")
    public String companyVacancyAdd(@RequestParam String title, @RequestParam String position,
                                    @RequestParam String salary, @RequestParam String requirements,
                                    @PathVariable(value = "companyId")  Long companyId,
                               Model model) {
        Company company = companyDAO.getById(companyId);
        Vacancy vacancy = new Vacancy(company, title);


        if(requirements != ""){
            vacancy.setRequirements(requirements);
        }
        if(position != ""){
            vacancy.setPosition(position);
        }
        if(salary != ""){
            Integer number = Integer.parseInt(salary);
            vacancy.setSalary(number);
        }
        vacancyDAO.save(vacancy);

        List<Vacancy> vacancyList = vacancyDAO.getVacanciesByFilter(company.getName(),null, null);
        model.addAttribute("company", company);
        model.addAttribute("vacancyList", vacancyList);
        return "companyVacancies";
    }

    @RequestMapping("/companyPage/{companyId}/vacancies/{vacancyId}")
    public String companyVacancyDelete(@PathVariable(value = "companyId")  Long companyId,
                                  @PathVariable(value = "vacancyId")  Long vacancyId,
                                  Model model) {
        vacancyDAO.deleteById(vacancyId);
        Company company = companyDAO.getById(companyId);
        List<Vacancy> vacancyList = vacancyDAO.getVacanciesByFilter(company.getName(),null, null);
        model.addAttribute("company", company);
        model.addAttribute("vacancyList", vacancyList);
        return "companyVacancies";
    }


    @RequestMapping("/companyPage/{companyId}/vacancies/{vacancyId}/responses")
    public String companyResponsesPage(@PathVariable(value = "companyId")  Long companyId,
                                  @PathVariable(value = "vacancyId")  Long vacancyId,
                                  Model model) {
        Company company = companyDAO.getById(companyId);
        Vacancy vacancy = vacancyDAO.getById(vacancyId);
        List<Response> responseList = responseDAO.getResponsesToVacancy(vacancy);
        model.addAttribute("responseList", responseList);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("company", company);
        return "companyResponses";
    }

    @RequestMapping("/companyPage/{companyId}/vacancies/{vacancyId}/responses/{seekerId}")
    public String companySeekerInfoPage(@PathVariable(value = "companyId")  Long companyId,
                                       @PathVariable(value = "seekerId")  Long seekerId,
                                       Model model) {
        Company company = companyDAO.getById(companyId);
        JobSeeker seeker = seekerDAO.getById(seekerId);
        List<PrevJob> jobList = prevJobDAO.getJobsToSeeker(seeker);
        model.addAttribute("company", company);
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobList", jobList);
        return "infoSeeker";
    }
}