package net.javaguides.sms.service.impl;

import net.javaguides.sms.entity.PasswordResetRequest;
import net.javaguides.sms.entity.User;
import net.javaguides.sms.service.PasswordResetRequestService;
import net.javaguides.sms.service.StudentService;
import net.javaguides.sms.service.UserService;
import net.javaguides.sms.utils.TokenGenerator;
import net.javaguides.sms.repository.PasswordResetRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetRequestServiceImpl implements PasswordResetRequestService {

    @Autowired
    private PasswordResetRequestRepository passwordResetRequestRepository;

    @Autowired
    private UserService userService; // Inject your UserService

    @Override
    public PasswordResetRequest generatePasswordResetToken(User user) {

        String token = TokenGenerator.generateUniqueToken();

        System.out.println(token);

        // Set expiration time to 1 hour from now
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

        System.out.println(expiryDate);

        PasswordResetRequest resetRequest = new PasswordResetRequest(token, user, expiryDate, false);

        System.out.println(resetRequest);

        return passwordResetRequestRepository.save(resetRequest);

    }


    // Other methods for validation, retrieval, etc.
}

