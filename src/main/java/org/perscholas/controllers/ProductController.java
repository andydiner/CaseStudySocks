package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.exceptions.ProductNotFoundException;
import org.perscholas.models.Product;
import org.perscholas.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"cart"})
@Controller
@Slf4j
public class ProductController {

    ProductServices productServices;
    String productRedirect = "redirect:/products";
    @Autowired
    public ProductController(ProductServices productServices){
        this.productServices = productServices;
    }

    @ModelAttribute("cart")
    public List<Product> initProduct(){
        return new ArrayList<Product>();
    }

    @GetMapping("/products")
    public String getProducts(Model model){
        List<Product> products = productServices.getAllProducts();
        log.warn("Products: " + products.size());
        model.addAttribute("products", products);
        return "products";
    }








    @GetMapping("/products/registerproduct")
    public String getRegisterProduct(Model model){
        model.addAttribute("product", new Product());
        return "registerproduct";
    }

    @PostMapping("/products/registerproduct")
    public String postRegisterProduct(Model model, @ModelAttribute("product") Product product){
        log.warn("PRODUCT NAME: " + product.getName() + " PRODUCT ID: " + product.getProductid());

        productServices.saveProduct(product);
        return productRedirect;
    }

//    @GetMapping("/productbyorder")
//    public String getProductByOrder(Model model){
//        model.addAttribute("product", new Product());
//        return "productbyemail";
//    }

//    @PostMapping("/productbyorder")
//    public String postProductByEmail(Model model, @ModelAttribute("product") @Valid List<Product> productList,
//                                     BindingResult bindingResult){
//        log.warn("Searching for " + productList.get());
//        product = productServices.getProductListByOrder(product.getEmailAddress());
//        model.addAttribute("product", product);
//        log.warn(product.getImagePath());
//        return "productprofile";
//    }


    @GetMapping("/products/edit/{productid}")
    public String showEditForm(@PathVariable("productid") Integer productid, Model model, RedirectAttributes redirectAttributes){
        try{
            Product product = productServices.get(productid);
            model.addAttribute("product", product);
            log.warn("Product Name to Edit: " + product.getName());
            return "updateproduct";
        } catch (ProductNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
            e.printStackTrace();
            return productRedirect;
        }
    }

    @PostMapping("/products/edit/updateproduct")
    public String saveUpdate(Product product, RedirectAttributes redirectAttributes){
        productServices.save(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully");
        return productRedirect;
    }

    @GetMapping("/products/delete/{productid}")
    public String deleteProduct(@PathVariable("productid") Integer productId, Model model,
                                RedirectAttributes redirectAttributes){
        try{
            log.warn("Trying to delete " + productServices.getProductByID(productId).getName());
            productServices.delete(productId);
            return productRedirect;
        } catch (ProductNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
            e.printStackTrace();
            return productRedirect;
        }
    }

    @GetMapping("/products/add/{productid}")
    public String addCart(@PathVariable("productid") Integer productid, Model model, RedirectAttributes redirectAttributes){
        try{
            Product product = productServices.get(productid);
            model.addAttribute("product", product);
            log.warn(product.getName() + " successfully added to cart.");
            return "products";
        } catch (ProductNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
            e.printStackTrace();
            return productRedirect;
        }
    }

    @PostMapping("/products/add/updateproduct")
    public String saveCart(Product product, RedirectAttributes redirectAttributes){
        productServices.save(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully");
        return productRedirect;
    }

}
