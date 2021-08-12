package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.models.Vendor;
import org.perscholas.services.VendorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/vendors/vendorbyemail")
    public String getVendorByEmail(Model model){
        model.addAttribute("vendor", new Vendor());
        return "vendorbyemail";
    }

    @PostMapping("/vendors/vendorbyemail")
    public String postVendorByEmail(Model model, @ModelAttribute("vendor") @Valid Vendor vendor,
                                  BindingResult bindingResult){
        log.warn("Searching for " + vendor.getEmailAddress());
        vendor = vendorServices.getVendorByEmail(vendor.getEmailAddress());
        model.addAttribute("vendor", vendor);

        return "vendorinfo";
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


    @ModelAttribute("currentVendor")
    public Vendor vendorInit(){
        return new Vendor();
    }
}
