package com.sparta.springnewsfeed.global.config;

import com.sparta.springnewsfeed.annotation.Auth;
import com.sparta.springnewsfeed.domain.user.dto.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    // @Auth 어노테이션이 있는지 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAuthAnnotation = parameter.getParameterAnnotation(Auth.class) != null;
        boolean isAuthUserType = parameter.getParameterType().equals(AuthUser.class);
        if(hasAuthAnnotation != isAuthUserType) {
            throw new IllegalArgumentException("@Auth Annotation 와 AuthUser Type 은 함꺠 사용되어야 합니다.");
        }
        return hasAuthAnnotation;
    }

    // AuthUser 객체를 생성하여 반환
    @Override
    public Object resolveArgument(
            @Nullable MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // JwtFilter 에서 set 한 userId, email 값을 가져옴
        Long userId = (Long) request.getAttribute("userId");
        String userName = (String) request.getAttribute("userName");
        String email = (String) request.getAttribute("email");

        return new AuthUser(userId, userName, email);
    }
}


