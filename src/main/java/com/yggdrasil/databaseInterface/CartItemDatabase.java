package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Cart;
import com.yggdrasil.model.CartItem;
import com.yggdrasil.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDatabase extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Cart id);
    List<CartItem> findByItemId(Item id);

    void deleteByCartId(Cart id);
}
