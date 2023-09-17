package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.DAO.JobSeekerDAO;
import ru.msu.cmc.webprak.DAO.ResponseDAO;
import ru.msu.cmc.webprak.DAO.VacancyDAO;
import ru.msu.cmc.webprak.DAO.impl.CompanyDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.JobSeekerDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.ResponseDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.VacancyDAOImpl;
import ru.msu.cmc.webprak.models.Company;
import ru.msu.cmc.webprak.models.JobSeeker;
import ru.msu.cmc.webprak.models.Response;
import ru.msu.cmc.webprak.models.Vacancy;


import java.util.List;

@Controller
public class VacancyController {

    @Autowired
    private final VacancyDAO vacancyDAO = new VacancyDAOImpl();

    @Autowired
    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @Autowired
    private final JobSeekerDAO seekerDAO = new JobSeekerDAOImpl();

    @Autowired
    private final ResponseDAO responseDAO = new ResponseDAOImpl();

    @GetMapping("/seekerPage/{seekerId}/vacancies")
    public String vacancyPage(@PathVariable(value = "seekerId")  Long seekerId, Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        List<Vacancy> vacancyList =(List<Vacancy>) vacancyDAO.getAll();
        model.addAttribute("vacancyList", vacancyList);
        model.addAttribute("seeker", seeker);
        return "vacancies";
    }

    @RequestMapping ("/seekerPage/{seekerId}/filter")
    public String vacancyFilterPage(@PathVariable(value = "seekerId")  Long seekerId,
                                    @RequestParam String company, @RequestParam String position,
                                    @RequestParam String salary,Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
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

        List<Vacancy> vacancyList = vacancyDAO.getVacanciesByFilter(company, position, sal);
        model.addAttribute("vacancyList", vacancyList);
        model.addAttribute("seeker", seeker);
        return "vacancies";
    }

    @GetMapping("/seekerPage/{seekerId}/vacancy/{id}")
    public String vacancyPage(@PathVariable(value = "seekerId")  Long seekerId,
                              @PathVariable(value = "id")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        model.addAttribute("seeker", seeker);

        Vacancy vacancy = vacancyDAO.getById(id);

        if (vacancy == null) {
            model.addAttribute("error_msg", "В базе нет вакансии с ID = " + id);
            return "errorPage";
        }

        model.addAttribute("vacancy", vacancy);
        return "vacancy";
    }

    @GetMapping("/seekerPage/{seekerId}/vacancy/{vacancyId}/click")
    public String addResponsePage(@PathVariable(value = "seekerId")  Long seekerId,
                              @PathVariable(value = "vacancyId")  Long vacancyId,
                              Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        Vacancy vacancy = vacancyDAO.getById(vacancyId);
        if (vacancy == null) {
            model.addAttribute("error_msg", "В базе нет вакансии с ID = " + vacancyId);
            return "errorPage";
        }
        responseDAO.save(new Response(vacancy,seeker));
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("seeker", seeker);
        return "vacancy";
    }

    @GetMapping("/seekerPage/{seekerId}/companyInfo/{id}")
    public String companyInfoPage(@PathVariable(value = "seekerId")  Long seekerId,
                                  @PathVariable(value = "id")  Long id, Model model) {
        JobSeeker seeker = seekerDAO.getById(seekerId);
        model.addAttribute("seeker", seeker);

        Company company = companyDAO.getById(id);

        if (company == null) {
            model.addAttribute("error_msg", "В базе нет компании с ID = " + id);
            return "errorPage";
        }

        model.addAttribute("company", company);
        return "companyInfo";
    }
}