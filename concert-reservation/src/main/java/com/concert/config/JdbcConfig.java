package com.concert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    // Spring Boot가 자동으로 DataSource를 제공하므로 명시적인 Bean 설정은 필요 없음
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);  // DataSource는 Spring Boot에서 자동으로 연결
    }
}
