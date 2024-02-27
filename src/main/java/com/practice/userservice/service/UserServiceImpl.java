package com.practice.userservice.service;

import com.practice.userservice.model.User;
import com.practice.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
       User userSaved  = userRepository.save(user);
       // send notifcation here
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8081/notify",user,Void.class);
        return userSaved;
    }
}
