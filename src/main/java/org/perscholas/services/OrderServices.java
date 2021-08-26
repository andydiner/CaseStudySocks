package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IOrderRepo;
import org.perscholas.exceptions.OrderNotFoundException;
import org.perscholas.models.Orders;
import org.perscholas.models.Product;
import org.perscholas.models.User;
import org.perscholas.models.Vendor;
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

    public Orders getCurrentOrderForCustomer(User customer){
        Orders orders = orderRepo.findOrderByCustomer(customer);
        log.warn(String.valueOf(orders));
        return orders;
    }
//    public List<Orders> getAllOrderForCustomer(User customer){
//        List<Orders> orders = orderRepo.findAllOrderForCustomer(customer);
//        log.warn(String.valueOf(orders));
//        return orders;
//    }
//    public List<Orders> getOrderByVendorList(List<Vendor> vendors){
//        List<Orders> orders = orderRepo.findOrderByVendorList(vendors);
//        log.warn(String.valueOf(orders));
//        return orders;
//    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        for (Orders o: orders) {
            List<Product> productList = o.getProductList();
            double totalPrice = 0L;
            for (Product p: productList) {
                totalPrice += p.getPrice();
            }
            o.setTotalPrice(totalPrice);

        }
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

    public double calculateTotal(Integer orderid){
        Optional<Orders> getOrder = orderRepo.findById(orderid);
        List<Product> productList = getOrder.get().getProductList();
        double totalPrice = 0;

        for(int i = 0; i < productList.size(); i++){
            totalPrice += productList.get(i).getPrice();
        }
        log.warn("ID: " + getOrder.get().getOrderid());
        log.warn("Total Price: " + totalPrice);
        return totalPrice;

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
