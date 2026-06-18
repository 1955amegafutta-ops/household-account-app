package com.example.householdaccountapi.controller;

import com.example.householdaccountapi.entity.User;
import com.example.householdaccountapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. ユーザー登録APIの窓口
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        try {
            // サービスを呼び出してデータベースに保存
            User registeredUser = userService.registerUser(user);

            // 「正常時の結果 (JSON)」を組み立てる
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("userId", registeredUser.getId());

            return new ResponseEntity<>(response, HttpStatus.CREATED); // Status: 201

        } catch (Exception e) {
            // 「エラー時の結果 (JSON)」を組み立てる
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Registration failed: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); // Status: 400
        }
    }
}