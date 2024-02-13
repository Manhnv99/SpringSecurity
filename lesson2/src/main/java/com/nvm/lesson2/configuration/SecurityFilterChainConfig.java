package com.nvm.lesson2.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //Disable CORS
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        //Disable CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //Http Request Filter
        //authorizeHttpRequest được sử dụng để cấu hình xác thực và thân quyền cho các yêu cầu Http trong trương trình.
        httpSecurity.authorizeHttpRequests(requestMatcher->
                requestMatcher.requestMatchers("/api/auth/login/**").permitAll() //đây là xác thực và phân quyền cụ thể của 1 enpoint nó sẽ áp dụng cho tất cả các yêu cầu bắt đầu bằng /api/auth/login và .permitAll là Cho phép tất cả các yêu cầu qua mà không cần xác thực.
                .requestMatchers("/api/auth/sign-up/**").permitAll()
                .anyRequest().authenticated() // ở đây nói rằng tất cả các enpoint khác mà không phải enpoint được chỉ định ở trên thì đều phải xác thực
        );
        //Authentication Entry Point -> Exception handler
        httpSecurity.exceptionHandling(
                exceptionConfig->exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
        );
        //Set sessions policy = STATELESS
        httpSecurity.sessionManagement(
                sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //không tạo ra phiên làm việc -> đồng nghĩa với việc quản lý phiên do JWT quản lý.
        );
        //Add JWT Authentication Filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }
}
