package net.javaguides.sms.service.impl;

import net.javaguides.sms.config.CustomUserDetails;
import net.javaguides.sms.dto.UserRegistrationDto;
//import net.javaguides.sms.entity.Role;
import net.javaguides.sms.entity.PasswordResetRequest;
import net.javaguides.sms.entity.User;

import net.javaguides.sms.entity.UserRole;
import net.javaguides.sms.repository.PasswordResetRequestRepository;
import net.javaguides.sms.repository.UserRepository;
import net.javaguides.sms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    /* @Autowired
        private BCryptPasswordEncoder passwordEncoder;

       */
    @Autowired
    private PasswordResetRequestRepository passwordResetRequestRepository; // Inject your repository

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    public boolean isUserTableEmpty() {
        long userCount = userRepository.count();
        return userCount == 0;
    }


    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                (registrationDto.getPassword()), UserRole.ROLE_USER.name());

        return userRepository.save(user);
    }

    @Override
    public void updateUserPassword(String username, String newPassword) {
        // Retrieve the user from the database based on the username
        User user = userRepository.findByEmail(username);

        if (user != null) {
            // Update the user's password
            user.setPassword(newPassword);
            userRepository.save(user); // Save the updated user entity
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(user);
    }
    public String getUserPassword(String email) {
        // Use the repository to fetch the user's password by username
        return userRepository.findPasswordByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        // Implement the logic to retrieve a user by email from the repository
        return userRepository.findByEmail(email);
    }

    @Override
    public PasswordResetRequest validatePasswordResetToken(String token) {
        // Find the PasswordResetRequest entity by token
        PasswordResetRequest resetRequest = passwordResetRequestRepository.findByToken(token);

        if (resetRequest == null || resetRequest.isUsed() || resetRequest.isExpired()) {
            // Token is invalid, used, or expired
            return null;
        }

        return resetRequest; // Token is valid
    }

    @Override
    public void resetUserPassword(User user, String newPassword) {

        // Set the new  password for the user
        user.setPassword(newPassword);

        // Save the updated user in the database
        userRepository.save(user);
    }

    @Override
    public void savePasswordResetRequest(PasswordResetRequest resetRequest) {
        passwordResetRequestRepository.save(resetRequest);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}