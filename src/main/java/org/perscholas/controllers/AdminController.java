package org.perscholas.controllers;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("console")
public class AdminController {
    @GetMapping("/getallcustomers")
    public String getUsers(Model model){

        return "getallcustomers";
    }

    // @PreAuthorize("hasAuthority('WRITE')")
    @GetMapping("/getcustomerbyemail")
    public String getCustomerByEmail(){

        return "getcustomerbyemail";
    }
}