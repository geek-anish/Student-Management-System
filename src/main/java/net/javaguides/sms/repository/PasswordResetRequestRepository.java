package net.javaguides.sms.repository;

import net.javaguides.sms.entity.PasswordResetRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequest, Long> {

    PasswordResetRequest findByToken(String token);



    // Additional methods if needed
}

