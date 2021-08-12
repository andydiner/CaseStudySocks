package org.perscholas.services;

import org.perscholas.dao.ICustomerRepo;
import org.perscholas.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServices {
    ICustomerRepo customerRepo;
    @Autowired
    public CustomerServices(ICustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }
    public Customer save(Customer c){
        return customerRepo.save(c);
    }
}
