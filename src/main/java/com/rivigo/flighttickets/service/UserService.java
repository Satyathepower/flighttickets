package com.rivigo.flighttickets.service;

import com.rivigo.flighttickets.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    public User saveUser(User user);
}
