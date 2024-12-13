package org.napbad.scoremanager.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Generated;
import org.napbad.scoremanager.util.JwtUtil;
import org.napbad.scoremanager.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

@Component
public class UserContextInterceptor
implements HandlerInterceptor {
//    @Generated
//    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);
//    private final AuthExcludePathProperties authExcludePathProperties;
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    public UserContextInterceptor(AuthExcludePathProperties authExcludePathProperties) {
//        this.authExcludePathProperties = authExcludePathProperties;
//    }
//
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//        if (isExcluded(request.getServletPath()).booleanValue()) {
//            UserContext.setUserId(0);
//            return true;
//        }
//
//        String token = request.getHeader("token");
//        Claims parsedJWT = JwtUtil.parseJWT("napbad", token);
//        String header = (String) parsedJWT.get("userId");
//        log.info("header: {}  value {} , request path: {}", "userId", header, request.getServletPath());
//
//        if (!StringUtils.hasLength(header)) {
//            return false;
//        }
//        UserContext.setUserId(Integer.valueOf(header));
//        return true;
//    }
//
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        UserContext.remove();
//    }
//
//    private Boolean isExcluded(String path) {
//        return this.authExcludePathProperties.getExcludePaths()
//                .stream().anyMatch(excludePath -> this.antPathMatcher.match(excludePath, path));
//    }
}

