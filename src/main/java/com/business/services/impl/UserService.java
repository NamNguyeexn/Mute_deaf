package com.business.services.impl;

import com.business.beans.User;
import com.business.repository.UserRepo;
import com.business.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        for (var b : userRepo.findAll()) {
            if (b.getUsername().compareTo(username) == 0) {
                if(b.getPassword().compareTo(password) == 0) {
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
}
