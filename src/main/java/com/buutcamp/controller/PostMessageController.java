package com.buutcamp.controller;

import com.buutcamp.config.AppConfig;
import com.buutcamp.databaselogic.ClientDao;
import com.buutcamp.objects.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;

@Controller
public class PostMessageController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/postmessage")
    public String showPostmessage(Model model, Authentication authentication) {

        model.addAttribute("newmessage", new NewMessage());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            System.out.println("User has authorities " + userDetails.getAuthorities());
            System.out.println("User name " + userDetails.getUsername());


            model.addAttribute("currentUser", userDetails.getUsername());


        return "postmessage";
    }

    @PostMapping("/processMessage")
    public String pocessMessage(
            @Valid @ModelAttribute ("newmessage") NewMessage newMessage, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //Test if input is correct
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("registrationError", "Title or message is too short or long");
            return "redirect:/postmessage";
        }

        String username = newMessage.getUserName();
        String title = newMessage.getTitle();
        String message = newMessage.getMessage();

        //create a java sql date object we want to insert
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaDateObject = new java.sql.Timestamp(calendar.getTime().getTime());

        System.out.println(username);
        System.out.println(title);
        System.out.println(message);

        clientDao.createRow(new Messages(0,ourJavaDateObject ,ourJavaDateObject ,title,message, username));

        System.out.println(username);
        System.out.println(title);
        System.out.println(message);


        return "redirect:/";
    }
}
