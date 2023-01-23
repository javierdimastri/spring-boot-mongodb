package com.javierdimastri.service;

import com.javierdimastri.model.User;

public interface UserService {
    Boolean isUserExistByUserName(String userName);
    Boolean isUserExistByEmail(String userName);
    void storedUser(User user);
}
