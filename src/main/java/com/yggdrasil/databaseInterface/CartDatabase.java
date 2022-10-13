package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDatabase extends JpaRepository<Cart, Long> {

    Cart findByToken(String token);
}
