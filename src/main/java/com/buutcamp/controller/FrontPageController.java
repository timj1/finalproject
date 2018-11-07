package com.buutcamp.controller;

import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller
public class FrontPageController {

    @GetMapping("/")
    public String showHome(Model model, Authentication authentication) {

        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(instant);
        System.out.println(zonedDateTime);
        System.out.println(localDateTime);


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
