//package com.sparta.springnewsfeed.AuthFilter;
//
//import com.sparta.springnewsfeed.domain.user.entity.User;
//import com.sparta.springnewsfeed.domain.user.repository.UserRepository;
//import com.sparta.springnewsfeed.jwt.JwtUtil;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
//@Slf4j(topic = "AuthFilter")
//@Component
//@Order(1)
//public class AuthFilter implements Filter{
//    private final UserRepository userRepository;
//    private final JwtUtil jwtUtil;
//
//    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String url = httpServletRequest.getRequestURI();
//
//        if (StringUtils.hasText(url) &&
//                (url.startsWith("/api/user/signup") || url.startsWith("/api/user/login"))
//        ) {
//            chain.doFilter(request, response); // 다음 Filter 로 이동
//        } else {
//            // 나머지 API 요청은 인증 처리 진행
//            // 토큰 확인
//            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);
//
//            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
//                // JWT 토큰 substring
//                String token = jwtUtil.substringToken(tokenValue);
//
//                // 토큰 검증
//                if (!jwtUtil.validateToken(token)) {
//                    throw new IllegalArgumentException("Token Error");
//                }
//
//                // 토큰에서 사용자 정보 가져오기
//                Claims info = jwtUtil.getUserInfoFromToken(token);
//
//                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(() -> {
//                            return new NullPointerException("Not Found User");
//                        }
//                );
//
//                request.setAttribute("userId", user.getId());
//                request.setAttribute("userName", user.getUsername());
//                request.setAttribute("email", user.getEmail());
//                chain.doFilter(request, response); // 다음 Filter 로 이동
//            } else {
//                throw new IllegalArgumentException("Not Found Token");
//            }
//        }
//    }
//
//}
