package com.project.esunfeed_back.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.project.esunfeed_back.Util.JwtAuthenticationFilter;
import com.project.esunfeed_back.Util.JwtService;
import com.project.esunfeed_back.Util.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF 保護
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 配置 CORS
                .headers(headers -> headers
                        .addHeaderWriter((request, response) -> {
                            // 設置 Cross-Origin-Opener-Policy 和 Cross-Origin-Embedder-Policy
                            // 標頭
                            response.setHeader("Cross-Origin-Opener-Policy",
                                    "same-origin-allow-popups");
                            response.setHeader("Cross-Origin-Embedder-Policy",
                                    "unsafe-none"); // 根據需求調整或禁用
                        }))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/public/**", "/api/v1/guest/**",
                                "/api/v1/user/websocketcon", "/oauth2/**",
                                "/login", "/ws/**")
                        .permitAll() // 允許公開訪問的路徑
                        .requestMatchers("/api/v1/user/**").hasRole("USER") // 需要 USER 角色的路徑
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // 需要 ADMIN 角色的路徑
                        .anyRequest().authenticated() // 其他請求需要認證
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 設定無狀態會話
                )
                .authenticationProvider(authenticationProvider()) // 使用自定義的 AuthenticationProvider
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 在
                                                                                                         // UsernamePasswordAuthenticationFilter
                                                                                                         // 之前添加
                                                                                                         // JWT
                                                                                                         // 過濾器

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // 設定自定義的 UserDetailsService
        provider.setPasswordEncoder(passwordEncoder()); // 設定密碼編碼器
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 BCrypt 進行密碼編碼
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // 允許所有來源
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允許的方法
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // 允許的標頭
        configuration.setAllowCredentials(true); // 不允許憑證
        configuration.setExposedHeaders(
                List.of("Authorization", "Cross-Origin-Opener-Policy", "Cross-Origin-Embedder-Policy")); // 暴露的標頭
        return request -> configuration; // 返回配置
    }
}
