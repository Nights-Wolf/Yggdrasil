package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Shipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentsDatabase extends JpaRepository<Shipments, Long> {
}
