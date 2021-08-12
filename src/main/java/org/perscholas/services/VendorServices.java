package org.perscholas.services;

import org.perscholas.dao.ICustomerRepo;
import org.perscholas.dao.IVendorRepo;
import org.perscholas.models.Customer;
import org.perscholas.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class VendorServices {

    IVendorRepo vendorRepo;
    @Autowired
    public VendorServices(IVendorRepo vendorRepo){
                this.vendorRepo = vendorRepo;
            }
            public Vendor save(Vendor v){
                return vendorRepo.save(v);
            }


}
