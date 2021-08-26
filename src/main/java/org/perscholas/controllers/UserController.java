package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.exceptions.UserNotFoundException;
import org.perscholas.models.User;
import org.perscholas.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@SessionAttributes({"currentUser"})
public class UserController {

    UserServices userServices;
    String userRedirect = "redirect:/users";
    @Autowired
    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public String getCustomers(Model model){
        List<User> users = userServices.getAllUsers();
        log.warn("Users: " + users.size());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/registeruser")
    public String getRegisterCustomer(Model model){
        model.addAttribute("user", new User());
        return "registeruser";
    }

    @PostMapping("/users/registeruser")
    public String postRegisterUser(Model model, @ModelAttribute("user") User user){
        log.warn("NAME: " + user.getFirstName() + " " + user.getLastName()
                + " " + user.getEmailAddress());

        userServices.saveUser(user);
        return userRedirect;
    }

    @GetMapping("/userbyemail")
    public String getUserByEmail(Model model){
        model.addAttribute("user", new User());
        return "userbyemail";
    }

    @PostMapping("/userbyemail")
    public String postUserByEmail(Model model, @ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult){
        log.warn("Searching for " + user.getEmailAddress());
        user = userServices.getUserByEmail(user.getEmailAddress());
        model.addAttribute("user", user);
        log.warn(user.getImagePath());
        return "userprofile";
    }

    @GetMapping("/users/login")
    public String getLogin(Model model){
        log.warn("Get Login...");
        model.addAttribute("currentUser", new User());
        return "login";
    }

    @PostMapping("/users/login")
    public String postLogin(Model model, @ModelAttribute("currentUser") @Valid User currentUser, BindingResult bindingResult){
        log.warn("Post login...");

        if (userServices.validateUser(currentUser)) {
            model.addAttribute("currentUser", currentUser);
            return "redirect:/index";
        }

        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString() + " There is an error with the username.");
        }
        return "login";
    }

    @GetMapping("/users/edit/{emailAddress}")
    public String showEditForm(@PathVariable("emailAddress") String email,
                               Model model, RedirectAttributes redirectAttributes){
            try{
                User user = userServices.get(email);
                model.addAttribute("user", user);
                return "updateuser";
            } catch (UserNotFoundException e){
                redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
                e.printStackTrace();
                return userRedirect;
            }
    }

    @PostMapping("/users/edit/updateuser")
    public String saveUpdate(User user, RedirectAttributes redirectAttributes){
        try {
            userServices.update(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "User saved successfully");
        return userRedirect;
    }

    @GetMapping("/users/delete/{emailAddress}")
    public String deleteUser(@PathVariable("emailAddress") String email, Model model, RedirectAttributes redirectAttributes){
        try{
            userServices.delete(email);
            return userRedirect;
        } catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
            e.printStackTrace();
            return userRedirect;
        }
    }


    @ModelAttribute("currentUser")
    public User userInit(){
        return new User();
    }
}
