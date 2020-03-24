package com.pzr.service;

import com.pzr.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
