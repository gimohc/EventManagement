package com.software.eventmanagement.user;

import com.software.eventmanagement.Cookies.CookieController;
import com.software.eventmanagement.entities.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user, HttpServletResponse response) {
        userService.save(user);
        CookieController.setUserCookie(response, user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (userService.verifyUser(request) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        CookieController.setUserCookie(response, request.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}