package org.napbad.scoremanager.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.napbad.scoremanager.util.JwtUtil;
import org.napbad.scoremanager.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

@Slf4j
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
public class AuthFilter extends OncePerRequestFilter {

    private final AuthExcludePathProperties authExcludePathProperties;

    public AuthFilter(AuthExcludePathProperties authExcludePathProperties) {
        this.authExcludePathProperties = authExcludePathProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        if (Objects.equals(request.getMethod(), "OPTIONS")) {

            try {
                filterChain.doFilter(request, response);
            } catch (ServletException e) {
                    throw new RuntimeException(e);
            }
            return;
        }

        try {
            // 打印请求的基本信息
            log.debug("Request received for path: {}", request.getServletPath());
            log.debug("Request method: {}", request.getMethod());

            // 打印请求头
            log.debug("Request headers:");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.debug("Header {}: {}", headerName, headerValue);
            }

            // 打印请求参数
            log.debug("Request parameters:");
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                log.debug("Parameter {}: {}", paramName, String.join(", ", paramValues));
            }

//            String requestBody = getRequestBody(request);
//            log.debug("Request body: {}", requestBody);

            String servletPath = request.getServletPath();
            log.debug("Request received for path: {}", servletPath);

            if (isExcluded(servletPath)) {
                log.debug("Path {} is excluded from authentication", servletPath);
                UserContext.remove();
                filterChain.doFilter(request, response);
                return;
            }

            String token = getTokenFromRequest(request);
            if (token != null) {
                log.debug("Token found in request: {}", token);
                Claims claims = JwtUtil.parseJWT("napbad", token);
                Object id = claims.get("id");

                if (id instanceof Integer) {
                    int userId = (Integer) id;
                    log.debug("User ID extracted from token: {}", userId);
                    UserContext.setUserId(userId);
                    filterChain.doFilter(request, response);
                    return;
                }

                String userIdStr = (String) claims.get("id");
                if (StringUtils.hasLength(userIdStr)) {
                    int userId = Integer.parseInt(userIdStr);
                    log.debug("User ID extracted from token as string and converted: {}", userId);
                    UserContext.setUserId(userId);
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            log.warn("No valid token found or user ID could not be extracted");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal Server Error");
        } finally {
            UserContext.remove();
            log.debug("User context removed after processing request");
        }
    }


    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        }
        return sb.toString();
    }
    private boolean isExcluded(String path) {
        return authExcludePathProperties.getExcludePaths()
                .stream().anyMatch(excludePath -> new AntPathMatcher().match(excludePath, path));
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Token");

        if (StringUtils.hasText(bearerToken)
//                && bearerToken.startsWith("Bearer ")
        ) {
            return bearerToken;
//            return bearerToken.substring(7);
        }
        return null;
    }
}

//curl --location --request POST 'http://8.137.96.68:8080/score/query' \
//        --header 'token: eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwiZXhwIjoxNzM0NTk1NTQwfQ.lfmAgDhJ46I7vP3frxlRBXVpX3etMJ_e3GgW5OofvMo' \
//        --header 'User-Agent: Apifox/1.0.0 (https://apifox.com)' \
//        --header 'Content-Type: application/json' \
//        --header 'Accept: */*' \
//        --header 'Host: 127.0.0.1:8080' \
//        --header 'Connection: keep-alive' \
//        --data-raw '{
//        "id": 0,
//        "minRegularScore": 0,
//        "minMidtermScore": 0
//        }'
