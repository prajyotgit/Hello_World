package com.axisrooms.pms.repo;

import com.axisrooms.pms.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    Optional<Inventory> findById(Long id);
}
