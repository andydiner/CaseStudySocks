package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IOrderRepo;
import org.perscholas.exceptions.OrderNotFoundException;
import org.perscholas.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderServices {

    IOrderRepo orderRepo;
   // IProductRepo productRepo;
    @Autowired
    public OrderServices(IOrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }
    public Orders save(Orders o){
        return orderRepo.save(o);
    }

    public Orders getOrderByID(Integer orderID){
        Orders orders = orderRepo.getById(orderID);
        log.warn(String.valueOf(orders));
        return orders;
    }

    public Orders getOrderByUserEmail(String email){
        Orders orders = orderRepo.findOrderByUserEmail(email);
        log.warn(String.valueOf(orders));
        return orders;
    }
    public Orders getOrderByVendorEmail(String email){
        Orders orders = orderRepo.findOrderByVendorEmail(email);
        log.warn(String.valueOf(orders));
        return orders;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        log.warn(String.valueOf(orders));
        return orders;
    }

    public void saveOrder(Orders orders) {
        orderRepo.save(orders);
    }

    public Orders get(Integer orderID) throws OrderNotFoundException {
        Optional<Orders> getOrder = orderRepo.findById(orderID);
        if(getOrder.isPresent()){
            return getOrder.get();
        }
        throw new OrderNotFoundException("Could not find any orderssses with id: " + orderID);
    }

    public void delete(Integer orderID) throws OrderNotFoundException {
        Optional<Orders> getOrder = orderRepo.findById(orderID);
        if(getOrder.isPresent()) {
            orderRepo.deleteById(orderID);
        }
        throw new OrderNotFoundException("Could not find any orderssses with id: " + orderID);
    }

//    public void addProduct(Long productID, Long orderID){
//        Optional<Product> addItem = productRepo.findById(productID);
//        Optional<Orders> addProd = orderRepo.findById(orderID);
//        List<Product> productList = addProd.get().getProductList();
//        if(addProd.isPresent()){
//            Product realProduct = addItem.orElse(null);
//            productList.add(realProduct);
//            log.warn("This product has been added to the order.");
//        }
//        log.warn("This product ID is faulty.");
//    }
}
