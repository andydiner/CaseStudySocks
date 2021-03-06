package org.perscholas.dao;

import org.perscholas.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendorRepo extends JpaRepository<Vendor, String> {
}
