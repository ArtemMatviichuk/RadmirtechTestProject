package com.test.radmirtech.dao.repos;

import com.test.radmirtech.dao.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
