package com.example.householdaccountapi.service;

import com.example.householdaccountapi.entity.User;
import com.example.householdaccountapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ユーザーを登録する処理
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}