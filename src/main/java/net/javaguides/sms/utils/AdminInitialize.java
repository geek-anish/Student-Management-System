package net.javaguides.sms.utils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
//import net.javaguides.sms.entity.Role;
import net.javaguides.sms.entity.User;
import net.javaguides.sms.entity.UserRole;
import net.javaguides.sms.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminInitialize {

    private final UserRepository userRepository;

    public AdminInitialize(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> areUsersWithRoleAdminExists() {
        List<User> userList = userRepository.existsByRole(UserRole.ROLE_ADMIN.name());
        return userList;
    }

    @PostConstruct
    public void initDefaultUsers() {
        // Add default users to the database

        if (areUsersWithRoleAdminExists().isEmpty()) {

        User user1 = new User();
        user1.setEmail("admin1@gmail.com");
        user1.setPassword("1");
        user1.setroles(UserRole.ROLE_ADMIN.name());
        userRepository.save(user1);
        }

    }
}
