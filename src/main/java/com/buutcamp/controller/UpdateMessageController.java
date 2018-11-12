package com.buutcamp.controller;

import com.buutcamp.databaselogic.ClientDao;
import com.buutcamp.objects.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class UpdateMessageController {

    @Autowired
    private ClientDao clientDao;

    @GetMapping("/updatemessage")
    public String showUpdateMessage (Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        //redirectAttributes.getFlashAttributes();

        model.addAttribute("newmessage", new NewMessage());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        model.addAttribute("currentUser", userDetails.getUsername());

        return "updatemessage";
    }

    @PostMapping("/processUpdate")
    public String pocessUpdate(
            @Valid @ModelAttribute("newmessage") NewMessage updateMessage, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication) {

        //Test if input is correct
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("registrationError", "Title or message is too short or long");
            return "redirect:/updatemessage";
        }

        //username from message
        String username = updateMessage.getUserName();

        //current user
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //test that current user is the same as message user
        if (!username.equals(userDetails.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationError", "No authorities to update this message!");
            return "redirect:/updatemessage";
        }

        int id = updateMessage.getId();
        String title = updateMessage.getTitle();
        String message = updateMessage.getMessage();


        //create a java sql date object we want to insert
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaDateObject = new java.sql.Timestamp(calendar.getTime().getTime());

        System.out.println(username);
        System.out.println(id);
        System.out.println(title);
        System.out.println(message);



        //clientDao.createRow(new Messages(0,ourJavaDateObject ,ourJavaDateObject ,title,message, username));
        clientDao.updateRow(new Messages(id,ourJavaDateObject,ourJavaDateObject,title,message,username));
        System.out.println(username);
        System.out.println(id);
        System.out.println(message);


        return "redirect:/";

    }
}
