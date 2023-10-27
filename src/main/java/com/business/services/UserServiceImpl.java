package com.business.services;

import com.business.beans.User;

public interface UserServiceImpl {
    User getUserByUsernameAndPassword (String username, String password);
    User save(User user);
}
