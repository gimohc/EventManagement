package com.software.eventmanagement.user;

import com.software.eventmanagement.entities.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<User> users = userRepository.findAll();
        for(User entity : users) {
            if(entity.getUsername().equals(user.getUsername()))
                return false;
        }
        userRepository.save(user);
        System.out.println("user created");
        return true;
    }
    public User verifyUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findById(loginRequest.getUsername());
        if(user.isPresent()) {
            User found = user.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(loginRequest.getPassword(), found.getPassword())) {
                System.out.println("organizer verified");
                return found;
            }
        }
        return null;
    }
}
