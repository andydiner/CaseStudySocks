package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IVendorRepo;
import org.perscholas.exceptions.VendorNotFoundException;
import org.perscholas.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class VendorServices {
    IVendorRepo vendorRepo;
    @Autowired
    public VendorServices(IVendorRepo vendorRepo){
        this.vendorRepo = vendorRepo;
    }
    public Vendor save(Vendor v){
        return vendorRepo.save(v);
    }
    public Vendor getVendorByEmail(String email){
        Vendor vendor = vendorRepo.getById(email);
        log.warn(String.valueOf(vendor));
        return vendor;
    }
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = vendorRepo.findAll();
        log.warn(String.valueOf(vendors));
        return vendors;
    }

    public void saveVendor(Vendor vendor) {
        vendorRepo.save(vendor);
    }

    public boolean validateVendor(Vendor vendor) {
        Vendor newVendor = this.getVendorByEmail(vendor.getEmailAddress());

        if (newVendor == null) {
            return false;
        }

        String uPassword = newVendor.getPassword();

        if (uPassword.equals(vendor.getPassword())) {
            return true;
        }
            return false;
    }

    public Vendor get(String email) throws VendorNotFoundException {
        Optional<Vendor> getVendor = vendorRepo.findById(email);
        if(getVendor.isPresent()){
            return getVendor.get();
        }
        throw new VendorNotFoundException("Could not find any vendors with email: " + email);
    }

    public void delete(String email) throws VendorNotFoundException {
        Optional<Vendor> getVendor = vendorRepo.findById(email);
        if(getVendor.isPresent()) {
            vendorRepo.deleteById(email);
        }
        throw new VendorNotFoundException("Could not find any vendors with email: " + email);
    }
}
