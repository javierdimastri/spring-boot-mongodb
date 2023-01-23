package com.javierdimastri.service;

import com.javierdimastri.model.User;
import com.javierdimastri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Boolean isUserExistByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }
    @Override
    public Boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public void storedUser(User user) {
        userRepository.save(user);
    }
}
