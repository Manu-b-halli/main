package com.jobportal.services;

import com.jobportal.entities.User;
import com.jobportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Welcome!", "Thank you for registering.");
    }

    public void generatePasswordResetToken(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String resetLink = "https://jobportal.com/reset-password?email=" + email;
            emailService.sendEmail(email, "Reset Password", "Click here: " + resetLink);
        }
    }
}
