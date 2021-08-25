package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.exceptions.UserNotFoundException;
import org.perscholas.exceptions.VendorNotFoundException;
import org.perscholas.models.User;
import org.perscholas.models.Vendor;
import org.perscholas.services.VendorServices;
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
@SessionAttributes({"currentVendor"})
public class VendorController {

    VendorServices vendorServices;
    @Autowired
    public VendorController(VendorServices vendorServices){
        this.vendorServices = vendorServices;
    }
    @GetMapping("/vendors")
    public String getVendors(Model model){
        List<Vendor> vendors = vendorServices.getAllVendors();
        log.warn("Vendors: " + vendors.size());
        model.addAttribute("vendors", vendors);
        return "vendors";
    }

    @GetMapping("/vendors/registervendor")
    public String getRegisterCustomer(Model model){
        model.addAttribute("vendor", new Vendor());
        return "registervendor";
    }

    @PostMapping("/vendors/registervendor")
    public String postRegisterVendor(Model model, @ModelAttribute("vendor") Vendor vendor){
        log.warn("NAME: " + vendor.getFirstName() + " " + vendor.getLastName()
                + " " + vendor.getEmailAddress());
        vendorServices.saveVendor(vendor);
        return "redirect:/vendors";
    }

    @GetMapping("/vendorbyemail")
    public String getVendorByEmail(Model model){
        model.addAttribute("vendor", new Vendor());
        return "vendorbyemail";
    }

    @PostMapping("/vendorbyemail")
    public String postVendorByEmail(Model model, @ModelAttribute("vendor") @Valid Vendor vendor,
                                  BindingResult bindingResult){
        log.warn("Searching for " + vendor.getEmailAddress());
        vendor = vendorServices.getVendorByEmail(vendor.getEmailAddress());
        model.addAttribute("vendor", vendor);

        return "vendorprofile";
    }

    @GetMapping("/vendors/vendorlogin")
    public String getLogin(Model model){
        log.warn("Get vendorlogin...");
        model.addAttribute("currentVendor", new Vendor());
        return "vendorlogin";
    }


    @PostMapping("/vendors/vendorlogin")
    public String postLogin(Model model, @ModelAttribute("currentVendor") @Valid Vendor currentVendor, BindingResult bindingResult){
        log.warn("Post vendorlogin...");

        if (vendorServices.validateVendor(currentVendor)) {
            model.addAttribute("currentVendor", currentVendor);
            return "redirect:/index";
        }

        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString() + " There is an error with the username.");
        }
        return "vendorlogin";
    }

    @GetMapping("/vendors/edit/{emailAddress}")
    public String showEditForm(@PathVariable("emailAddress") String email, Model model, RedirectAttributes redirectAttributes){
        try{
            Vendor vendor = vendorServices.get(email);
            model.addAttribute("vendor", vendor);
            return "updatevendor";
        } catch (VendorNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The vendor has been saved successfully.");
            e.printStackTrace();
            return "redirect:/vendors";
        }
    }

    @PostMapping("/vendors/edit/updatevendor")
    public String saveUpdate(Vendor vendor, RedirectAttributes redirectAttributes){
        vendorServices.save(vendor);
        redirectAttributes.addFlashAttribute("message", "Vendor saved successfully");
        return "redirect:/vendors";
    }

    @GetMapping("/vendors/delete/{emailAddress}")
    public String deleteVendor(@PathVariable("emailAddress") String email, Model model, RedirectAttributes redirectAttributes){
        try{
            vendorServices.delete(email);
            return "redirect:/vendor";
        } catch (VendorNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The vendor has been saved successfully.");
            e.printStackTrace();
            return "redirect:/vendors";
        }
    }

    @ModelAttribute("currentVendor")
    public Vendor vendorInit(){
        return new Vendor();
    }
}
