package org.perscholas.dao;

import org.perscholas.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}
