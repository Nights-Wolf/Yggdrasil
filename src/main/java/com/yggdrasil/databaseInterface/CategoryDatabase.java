package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDatabase extends JpaRepository<Category, Long> {

}
