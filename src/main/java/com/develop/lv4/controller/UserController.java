package com.develop.lv4.controller;

import com.develop.lv4.dto.UserLoginRequest;
import com.develop.lv4.dto.UserSignupRequest;
import com.develop.lv4.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserSignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok(Map.of("message", "회원가입이 완료되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest request, HttpServletRequest httpServletRequest) {
        userService.login(request, httpServletRequest);
        return ResponseEntity.ok(Map.of("message", "로그인 되었습니다."));
    }
}