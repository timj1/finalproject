package com.buutcamp.controller;

import com.buutcamp.databaselogic.ClientDao;
import com.buutcamp.objects.Messages;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showUpdateMessage(Model model) {

        model.addAttribute("updatemessage", new UpdateMessage());

        return "updatemessage";
    }

    @PostMapping("/processUpdate")
    public String pocessUpdate(
            @Valid @ModelAttribute("updatemessage") UpdateMessage updateMessage, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //Test if input is correct
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("registrationError", "Title or message is too short or long");
            return "redirect:/updatemessage";
        }

        String username = updateMessage.getUserName();
        int id = updateMessage.getId();
        String message = updateMessage.getMessage();

        // (2) create a java sql date object we want to insert
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaDateObject = new java.sql.Timestamp(calendar.getTime().getTime());

        System.out.println(username);
        System.out.println(id);
        System.out.println(message);

        //clientDao.createRow(new Messages(0,ourJavaDateObject ,ourJavaDateObject ,title,message, username));

        System.out.println(username);
        System.out.println(id);
        System.out.println(message);


        return "redirect:/";

    }
}
