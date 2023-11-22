package com.sapient.user.service.impl;

import com.sapient.user.service.entities.Attendance;
import com.sapient.user.service.entities.User;
import com.sapient.user.service.exceptions.ResourceNotFoundException;
import com.sapient.user.service.repositories.UserRepository;
import com.sapient.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id npt found on server" + userId));
        logger.info(userId);
        ArrayList<Attendance> attendancesOfUser = restTemplate.getForObject("http://localhost:8082/attendance/users/"+user.getUserId(), ArrayList.class);
        user.setAttendance(attendancesOfUser);
        return user;
    }
}
