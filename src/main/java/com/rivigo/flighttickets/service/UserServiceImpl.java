package com.rivigo.flighttickets.service;

import com.rivigo.flighttickets.entity.User;
import com.rivigo.flighttickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
