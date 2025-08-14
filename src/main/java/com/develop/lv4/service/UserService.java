package com.develop.lv4.service;

import com.develop.lv4.dto.UserLoginRequest;
import com.develop.lv4.dto.UserSignupRequest;
import com.develop.lv4.entity.User;
import com.develop.lv4.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String AUTH_SESSION = "auth_session";
    private final UserRepository userRepository;

    public void signup(UserSignupRequest request) {
        // 이메일 중복 확인
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        });

        // 비밀번호 암호화는 도전 과제에서 수행
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        userRepository.save(user);
    }

    public void login(UserLoginRequest request, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(AUTH_SESSION, user.getId());
    }
}