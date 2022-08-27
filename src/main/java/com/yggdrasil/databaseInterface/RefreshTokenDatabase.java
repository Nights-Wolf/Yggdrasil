package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDatabase extends JpaRepository<RefreshToken, Long> {
}
