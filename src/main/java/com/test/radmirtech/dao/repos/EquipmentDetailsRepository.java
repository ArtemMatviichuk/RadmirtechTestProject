package com.test.radmirtech.dao.repos;

import com.test.radmirtech.dao.domain.EquipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EquipmentDetailsRepository extends JpaRepository<EquipmentDetails, Long> {
    @Query(value = "SELECT det.* " +
            "FROM equipment_details det " +
            "JOIN equipment eq ON eq.details_id = det.id " +
            "WHERE eq.id = :id",
            nativeQuery = true)
    Optional<EquipmentDetails> findByEquipmentId(@Param("id") Long id);
}
