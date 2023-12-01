package net.javaguides.sms.controller;

import net.javaguides.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangePasswordController {

@Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "change-password"; // Return the Thymeleaf HTML template
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {
        // Implemented password change logic here

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();// Retrieve the current user's username


        // Retrieved the user's current password from the database
        String currentPassword = userService.getUserPassword(email);
        // Check if oldPassword matches the current password, validate newPassword, update the password, etc.

        // Check if the current password exists and is not null
        if (currentPassword == null) {
            // Handle the case where the current password is not available
            model.addAttribute("error", "Unable to retrieve the current password.");
            return "change-password"; // Return the change password form with an error message
        }

        // Check if oldPassword matches the current password, validate newPassword, update the password, etc.
        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            // Passwords don't match, return an error message
            model.addAttribute("error", "Old password is incorrect.");
            return "change-password"; // Return the change password form with an error message
        }

        if (!newPassword.equals(confirmPassword)) {
            // Passwords don't match, return an error message
            model.addAttribute("error", "New passwords do not match.");
            return "change-password"; // Return the change password form with an error message
        }


        userService.updateUserPassword(email, newPassword);

        // Redirect to a success page or display an error message

        return "redirect:/students?success";

    }
}
