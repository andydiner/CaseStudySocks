package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IOrderRepo;
import org.perscholas.dao.IProductRepo;
import org.perscholas.exceptions.ProductNotFoundException;
import org.perscholas.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductServices {

    IProductRepo productRepo;
    IOrderRepo orderRepo;

    @Autowired
    public ProductServices(IProductRepo productRepo, IOrderRepo orderRepo){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }
    public Product save(Product p){
        return productRepo.save(p);
    }

    public Product getProductByID(Integer productID){
        Product product = productRepo.getById(productID);
        log.warn("Product got: " + product.getName());
        return product;
    }
//
    public List<Product> getProductListByOrder(Integer orderID){
        List<Product> productList = orderRepo.getById(orderID).getProductList();
        log.warn(String.valueOf(productList));
        return productList;
    }
//    public Product addProductToVendor(Long productID, String vendorEmail){
//
//        Optional<Product> product = productRepo.findById(productID);
//        product.get().set
//
//    }
//
//    public List<Product> getProductsByOrder(Long orderID){
//        List<Product> products = productRepo.findProductsByOrder(orderID);
//        log.warn(String.valueOf(products));
//        return products;
//    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        log.warn(String.valueOf(products));
        return products;
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }



    public Product get(Integer productID) throws ProductNotFoundException {
        Optional<Product> getProduct = productRepo.findById(productID);
        if(getProduct.isPresent()){
            return getProduct.get();
        }
        throw new ProductNotFoundException("Could not find any products with id: " + productID);
    }

    public void delete(Integer productID) throws ProductNotFoundException {
        Optional<Product> getProduct = productRepo.findById(productID);
        if(getProduct.isPresent()) {
            log.warn("Product Name Deleted: " + getProduct.get().getName());
            Product realProduct = getProduct.orElse(null);
            productRepo.delete(realProduct);
           // productRepo.deleteById(productID);
        }
        throw new ProductNotFoundException("Could not find any products with id: " + productID);
    }
}

