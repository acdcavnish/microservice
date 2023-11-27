package com.sapient.user.service.controllers;

import com.sapient.user.service.entities.User;
import com.sapient.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body((user1));
    }
    @CircuitBreaker(name = "attendanceBreaker", fallbackMethod = "attendanceFallback")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    //creating fallback method for circuit breaker

    public ResponseEntity<User> attendanceFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = User.builder().email("dummy@gmail.com").name("dummy").about("This user is create dummy because some services are down").userId("124183128").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

}
