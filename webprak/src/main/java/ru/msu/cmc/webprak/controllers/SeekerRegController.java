package ru.msu.cmc.webprak.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.JobSeekerDAO;
import ru.msu.cmc.webprak.DAO.impl.JobSeekerDAOImpl;
import ru.msu.cmc.webprak.models.JobSeeker;

@Controller
public class SeekerRegController {
    @Autowired
    private final JobSeekerDAO seekerDAO = new JobSeekerDAOImpl();

    @GetMapping("/seekerRegistration")
    public String seekerRegPage(Model model) {
        return "seekerRegistration";
    }

    @PostMapping("/seekerRegistration")
    public String addSeeker(@RequestParam String login, @RequestParam String password, Model model) {
        seekerDAO.save(new JobSeeker( login,password));
        return "seekerLogin";
    }
}