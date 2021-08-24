package org.perscholas.controllers;

import org.perscholas.dao.IProductRepo;
import org.perscholas.models.User;
import org.perscholas.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

//    @GetMapping("/template")
//    public String template(){
//        return "template";
//    }

    private UserServices userServices;


    @ModelAttribute("theuser")
    public User theUserInit(){
        return new User();
    }
    @ModelAttribute("user")
    public User userInit(){
        return new User();
    }

    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
