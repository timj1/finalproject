package com.buutcamp.controller;

import com.buutcamp.databaselogic.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontPageController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/")
    public String showHome(Model model, Authentication authentication) {

        String messages = toString().valueOf(clientDao.getAllData());

        model.addAttribute("messagee", clientDao.getAllData().toArray());
        model.addAttribute("messaget", clientDao.getAllData().get(1).getMessage());
        model.addAttribute("messaget2", clientDao.getAllData().get(0));
        model.addAttribute("messageList", messages);

        System.out.println(messages);
        System.out.println(clientDao.getAllData().get(1).getMessage());
        System.out.println(clientDao.getAllData().get(0));
        return "homepage";
    }
    @GetMapping("/editpage")
    public String showEditPage(Model model) {

        return "editpage";
    }
    @GetMapping("/logout.done")
    public String logout() {

        return "redirect:/";
    }

    @GetMapping("/manager")
    public String showManager() {
        return "manager-page";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }
}
