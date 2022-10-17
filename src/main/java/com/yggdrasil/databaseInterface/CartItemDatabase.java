package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDatabase extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long id);
    List<CartItem> findByItemId(Long id);
}