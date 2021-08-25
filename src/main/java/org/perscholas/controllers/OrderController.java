package org.perscholas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.exceptions.OrderNotFoundException;
import org.perscholas.models.Orders;
import org.perscholas.services.OrderServices;
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
public class OrderController {

    OrderServices orderServices;
    String orderRedirect = "redirect:/orders";
    @Autowired
    public OrderController(OrderServices orderServices){
        this.orderServices = orderServices;
    }

    @ModelAttribute("cart")
    public List<Orders> initOrders(){
        return new ArrayList<Orders>();
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        List<Orders> orders = orderServices.getAllOrders();
        log.warn("Orders: " + orders.size());
        model.addAttribute("orders", orders);
        return "orders";
    }








    @GetMapping("/orders/registerorder")
    public String getRegisterOrders(Model model){
        model.addAttribute("order", new Orders());
        return "registerorder";
    }

    @PostMapping("/orders/registerorder")
    public String postRegisterOrders(Model model, @ModelAttribute("order") Orders order){
        log.warn("PRODUCT NAME: " + order.getUserEmail() + " PRODUCT ID: " + order.getVendorEmail() + order.getTotalPrice());

        orderServices.saveOrder(order);
        return orderRedirect;
    }

//    @GetMapping("/ordersbyorder")
//    public String getOrdersByOrder(Model model){
//        model.addAttribute("orders", new Orders());
//        return "ordersbyemail";
//    }

//    @PostMapping("/ordersbyorder")
//    public String postOrdersByEmail(Model model, @ModelAttribute("orders") @Valid List<Orders> ordersList,
//                                     BindingResult bindingResult){
//        log.warn("Searching for " + ordersList.get());
//        orders = ordersServices.getOrdersListByOrder(orders.getEmailAddress());
//        model.addAttribute("orders", orders);
//        log.warn(orders.getImagePath());
//        return "ordersprofile";
//    }


    @GetMapping("/orders/edit/{orderid}")
    public String showEditForm(@PathVariable("orderid") Integer orderid,
                               Model model, RedirectAttributes redirectAttributes){
        try{
            Orders order = orderServices.get(orderid);
            model.addAttribute("order", order);
            log.warn("Orders Name to Edit: " + order.getOrderid());
            return "updateorder";
        } catch (OrderNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The order has been saved successfully.");
            e.printStackTrace();
            return orderRedirect;
        }
    }

    @PostMapping("/orders/edit/updateorder")
    public String saveUpdate(Orders order, RedirectAttributes redirectAttributes){
        orderServices.save(order);
        redirectAttributes.addFlashAttribute("message", "Order saved successfully");
        return orderRedirect;
    }

    @GetMapping("/orders/delete/{orderid}")
    public String deleteOrders(@PathVariable("orderid") Integer orderId, Model model,
                               RedirectAttributes redirectAttributes){
        try{
            log.warn("Trying to delete " + orderServices.getOrderByID(orderId).getOrderid());
            orderServices.delete(orderId);
            return orderRedirect;
        } catch (OrderNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The order has been deleted successfully.");
            e.printStackTrace();
            return orderRedirect;
        }
    }

    @GetMapping("/orders/add/{orderid}")
    public String addCart(@PathVariable("orderid") Integer orderid, Model model,
                          RedirectAttributes redirectAttributes){
        try{
            Orders order = orderServices.get(orderid);
            model.addAttribute("orders", order);
            log.warn(order.getOrderid() + " successfully added to cart.");
            return "orderss";
        } catch (OrderNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "The orders has been saved successfully.");
            e.printStackTrace();
            return orderRedirect;
        }
    }

    @PostMapping("/orders/add/updateorder")
    public String saveCart(Orders order, RedirectAttributes redirectAttributes){
        orderServices.save(order);
        redirectAttributes.addFlashAttribute("message", "Order added successfully");
        return orderRedirect;
    }

}

