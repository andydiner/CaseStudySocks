package org.perscholas.dao;

import org.perscholas.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Integer> {
    Orders findOrderByUserEmail(String email);
    Orders findOrderByVendorEmail(String email);

}
