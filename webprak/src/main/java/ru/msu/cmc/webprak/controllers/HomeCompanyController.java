package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.CompanyDAO;
import ru.msu.cmc.webprak.DAO.impl.CompanyDAOImpl;
import ru.msu.cmc.webprak.models.Company;


@Controller
public class HomeCompanyController {

    @Autowired
    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @GetMapping("/indexCompany")
    public String indexCompany(Model model) {
        return "indexCompany";
    }

    @GetMapping("/companyRegistration")
    public String companyRegPage(Model model) {
        return "companyRegistration";
    }

    @PostMapping("/companyRegistration")
    public String addCompany(@RequestParam String login, @RequestParam String password, Model model) {
        companyDAO.save(new Company(login, password));
        return "companyLogin";
    }

    @GetMapping("/companyLogin")
    public String companyLoginPage() {
        return "companyLogin";
    }


    @PostMapping("/companyLogin")
    public String companyLogin(@RequestParam String login, @RequestParam String password, Model model) {
        Company company = companyDAO.getUser(login,password);
        if (company == null) {
            model.addAttribute("error_msg", "Некорректный логин или пароль");
            return "errorPage";
        }
        model.addAttribute("company", company);
        return "companyPage";
    }
}