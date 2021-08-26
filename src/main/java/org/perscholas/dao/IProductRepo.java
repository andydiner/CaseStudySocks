package org.perscholas.dao;

import org.perscholas.models.Product;
import org.perscholas.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Integer> {
    //public Vendor findVendorEmail(Integer productid);
}
