package org.perscholas.dao;

import org.perscholas.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendorRepo extends JpaRepository<Vendor, Long> {
}
