package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.exceptions.ProductNotFoundException;
import org.perscholas.exceptions.UserNotFoundException;
import org.perscholas.models.Orders;
import org.perscholas.models.Product;
import org.perscholas.models.User;
import org.perscholas.services.OrderServices;
import org.perscholas.services.ProductServices;
import org.perscholas.services.UserServices;
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
    OrderServices orderServices;
    UserServices userServices;
    String productRedirect = "redirect:/products";
    String cartRedirect = "redirect:/cart";
    String email = "adiner@asu.edu";

    @Autowired
    public ProductController(ProductServices productServices, OrderServices orderServices,
                             UserServices userServices){
        this.productServices = productServices;
        this.orderServices = orderServices;
        this.userServices = userServices;
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
        User user = userServices.getUserByEmail(email);
        log.warn("USER EMAIL: " + user.getEmailAddress());
        Orders order = orderServices.getCurrentOrderForCustomer(user);
        log.warn("CURRENT ORDER " + order.toString());
        List<Orders> allOrders = orderServices.getAllOrders();
        log.warn("AllOrders list initialized.");
        for(Orders o: allOrders){
            log.warn("Starting to Calculate all totals");
            orderServices.calculateTotal((o.getOrderid()));
        }
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

//    @GetMapping("/productsbyvendor")
//    public String getProductByVendor(Model model){
//        model.addAttribute("product", new Product());
//        return "productbyvendor";
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
    public String showEditForm(@PathVariable("productid") Integer productid,
                               Model model, RedirectAttributes redirectAttributes){
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
        try {
            productServices.update(product);

        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }

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
//
    @GetMapping("/products/add/{productid}")
    public String addCart(@PathVariable("productid") Integer productid,
                          Model model, RedirectAttributes redirectAttributes){
        try{
            Product product = productServices.get(productid);
            model.addAttribute("product", product);
            User user = userServices.getUserByEmail(email);
            model.addAttribute("user", user);
            log.warn("The Owner of " + product.getName() + " is: " + productServices.findVendorEmail(1).toString());

            Orders currentOrder = new Orders();
              if(currentOrder == null){
                  currentOrder = new Orders();
                  currentOrder.setCustomer(user);
              }else {
                  currentOrder = orderServices.getCurrentOrderForCustomer(user);
              }
              currentOrder.getProductList().add(product);
              log.warn(
                      "User email: " + userServices.getUserByEmail(email)
                      + "Current Order ID: " + currentOrder.getOrderid()
                      + "Current Product Added: " + product
              );
            model.addAttribute("order", currentOrder);


           // orderServices.calculateTotal(currentOrder.getOrderid());
          //  log.warn("Total Price: " + orderServices.calculateTotal(currentOrder.getOrderid()));

            log.warn(product.getName() + " successfully added to cart.");
            return cartRedirect;
        } catch (ProductNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The product has been saved successfully.");
            e.printStackTrace();
            return productRedirect;
        }
    }



    @GetMapping("/cart")
    public String getCart(Model model){
        log.warn("Entered Cart");
        User user = userServices.getUserByEmail(email);
        log.warn("USER EMAIL: " + user.getEmailAddress());
        Orders order = orderServices.getCurrentOrderForCustomer(user);
        log.warn("CURRENT ORDER " + order.toString());
        List<Orders> allOrders = orderServices.getAllOrders();
        log.warn("AllOrders list initialized.");
        for(Orders o: allOrders){
            log.warn("Starting to Calculate all totals");
            orderServices.calculateTotal((o.getOrderid()));
        }
       // double total = orderServices.calculateTotal(order.getOrderid());
        //orderServices.calculateTotal(order.getOrderid());
        log.warn("Total Price Update: " + order.getTotalPrice());
        List<Product> products = order.getProductList();
        log.warn("Products: " + products.size());
        log.warn("Product List: ");
        for(int i = 0; i < products.size(); i++){
            log.warn(products.get(i).getName());
        }
        model.addAttribute("products", products);
        model.addAttribute("order", order);
        return "cart";
    }

    @GetMapping("/cart/delete/{productid}")
    public String deleteFromCart(@PathVariable("productid") Integer productId, Model model,
                                RedirectAttributes redirectAttributes){
        User user = userServices.getUserByEmail(email);
        Orders order = orderServices.getCurrentOrderForCustomer(user);
        Product product = productServices.getProductByID(productId);
            log.warn("Trying to remove " + productServices.getProductByID(productId).getName());
            order.getProductList().remove(product);
           // orderServices.calculateTotal(order.getOrderid());
          //  log.warn("Total Price: " + orderServices.calculateTotal(order.getOrderid()));
        List<Product> products = orderServices.getCurrentOrderForCustomer(user).getProductList();


        log.warn("Product List Post Delete: ");
        for(int i = 0; i < products.size(); i++){
                log.warn(products.get(i).getName());
            }
            return cartRedirect;

    }

    @PostMapping("/products/add/updateproduct")
    public String saveCart(Product product, RedirectAttributes redirectAttributes){
        productServices.save(product);
        redirectAttributes.addFlashAttribute("message", "Product saved successfully");
        return productRedirect;
    }

}
