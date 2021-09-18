package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDatabase extends JpaRepository<Transactions, Long> {

}