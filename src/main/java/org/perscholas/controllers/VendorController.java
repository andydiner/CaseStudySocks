package org.perscholas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vendor")
public class VendorController {

    @GetMapping("/registervendor")
    public String registerVendor(){
        return "registervendor";
    }
}
