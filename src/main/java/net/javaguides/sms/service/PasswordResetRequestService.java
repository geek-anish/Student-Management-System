package net.javaguides.sms.service;

import net.javaguides.sms.entity.PasswordResetRequest;
import net.javaguides.sms.entity.User;

public interface PasswordResetRequestService {

    PasswordResetRequest generatePasswordResetToken(User user);



//    PasswordResetRequest findByToken(String token);
//
//    void markAsUsed(PasswordResetRequest request);
//
//    boolean isTokenExpired(PasswordResetRequest resetRequest);
    // Other methods for validation, retrieval, etc.
}

