package org.napbad.scoremanager.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsFilter implements Filter {
//
//    private static final String OPTIONS = "OPTIONS";
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse) response;
//        res.addHeader("Access-Control-Allow-Credentials", "true");
//        res.addHeader("Access-Control-Allow-Origin", "*");
//        res.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
//        res.addHeader("Access-Control-Allow-Headers", "*");
//        res.addHeader("Access-Control-Max-Age", "3600");
//
//        // 如果是OPTIONS则结束请求
//        if (OPTIONS.equals(((HttpServletRequest) request).getMethod())) {
//            response.getWriter().println("ok");
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public boolean isLoggable(LogRecord logRecord) {
//        return false;
//    }
//}


@Configuration
public class CorsConfig {

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOriginPatterns(Arrays.asList("*")); // 允许所有源进行访问
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // 允许的方法
//        config.setAllowCredentials(true); // 是否发送cookie
//        config.setMaxAge(168000L); // 预检间隔时间
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // 对所有路径应用 CORS 配置
//
//        return new CorsFilter(source);
//    }
}