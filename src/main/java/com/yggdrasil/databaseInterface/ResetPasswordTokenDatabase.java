package com.yggdrasil.databaseInterface;

import com.yggdrasil.model.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenDatabase extends JpaRepository<ResetPasswordToken, Long> {

    ResetPasswordToken findByToken(String token);
}
