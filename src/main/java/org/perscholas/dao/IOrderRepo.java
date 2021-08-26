package org.perscholas.dao;

import org.perscholas.models.Orders;
import org.perscholas.models.User;
import org.perscholas.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Integer> {
    Orders findOrderByCustomer(User customer);

}
