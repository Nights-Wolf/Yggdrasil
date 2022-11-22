package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Category;
import com.yggdrasil.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDatabase extends JpaRepository<Item, Long> {

    List<Item> findByCategoryId(Category categoryId);
}
