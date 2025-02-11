package com.concert.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CORS 설정을 추가
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 다른 보안 설정
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/api/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()  // 모든 사용자에게 허용하도록 설정
        ).csrf(csrf -> csrf.disable());

        return http.build();
    }

    // CORS 설정을 반환하는 메서드
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 모든 출처를 허용 (환경에 맞게 조정 가능)
        config.setAllowedOrigins(Arrays.asList("*"));

        // 허용할 HTTP 메서드 (GET, POST 등)
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 허용할 헤더 설정
        config.setAllowedHeaders(Arrays.asList("*"));

        // CORS 설정을 모든 경로에 적용
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
