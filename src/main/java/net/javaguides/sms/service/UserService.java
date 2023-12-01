package net.javaguides.sms.service;

import net.javaguides.sms.dto.UserRegistrationDto;
import net.javaguides.sms.entity.PasswordResetRequest;
import net.javaguides.sms.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username);

    void updateUserPassword(String name, String newPassword);

    String getUserPassword(String Email);

    User getUserByEmail(String email);

    PasswordResetRequest validatePasswordResetToken(String token);

    void resetUserPassword(User user, String newPassword);

    void savePasswordResetRequest(PasswordResetRequest resetRequest);

    void updatePassword(String password, Long userId);

    User findByEmail(String email);


}