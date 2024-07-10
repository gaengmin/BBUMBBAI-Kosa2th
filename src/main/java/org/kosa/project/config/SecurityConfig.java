package org.kosa.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kosa.project.security.RequiredAuthorizationUrlMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RequiredAuthorizationUrlMatcher matcher = new RequiredAuthorizationUrlMatcher(permitAllUrlPatterns());

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/meeting/insertMeeting").authenticated()
                        .requestMatchers(permitAllUrlPatterns()).permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .addLogoutHandler((request, response, authentication) -> {
                            System.out.println("SecurityConfig.logout");
                        })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String redirectUrl = request.getRequestURI(); //
                            // resource i
                            System.out.println(redirectUrl); // 리소스 위치
                            System.out.println(request.getRequestURL().toString()); // 호스트 : 리소스 위치
                            if (matcher.matches(request)) {
                                response.sendRedirect(redirectUrl);
                            } else {
                                response.sendRedirect("/");
                            }
                        })
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return http.build();
    }

    private String[] permitAllUrlPatterns() {
        return new String[]{"/*", "/css/**", "/meeting/*", "/js/**", "/imgs/**", "/users/join", "/users/**", "/home"};
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
