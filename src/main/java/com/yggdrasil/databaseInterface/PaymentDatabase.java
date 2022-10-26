package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDatabase extends JpaRepository<Payment, Long> {
}
