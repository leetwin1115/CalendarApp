package com.develop.lv4.filter;

import com.develop.lv4.entity.User;
import com.develop.lv4.repository.UserRepository;
import com.develop.lv4.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private static final String[] whitelist = {"/api/users/signup", "/api/users/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isWhitelist(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute(UserService.AUTH_SESSION) == null) {
            log.info("인증되지 않은 사용자 요청: {}", requestURI);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return;
        }

        Long userId = (Long) session.getAttribute(UserService.AUTH_SESSION);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ServletException("사용자를 찾을 수 없습니다.")
        );

        request.setAttribute(UserService.AUTH_SESSION, user);

        chain.doFilter(request, response);
    }

    private boolean isWhitelist(String requestURI) {
        return PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}