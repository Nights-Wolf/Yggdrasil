package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface OrdersDatabase extends JpaRepository<Orders, Long> {

    ArrayList<Orders> findByUserEmail(String email);
    Orders findByCartId(Long id);
}
