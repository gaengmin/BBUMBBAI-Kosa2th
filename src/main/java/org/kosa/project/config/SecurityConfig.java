package org.kosa.project.config;

import jakarta.servlet.http.HttpServletRequest;
import org.kosa.project.security.RequiredAuthorizationUrlMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email") // user, password
                        .passwordParameter("password")
                        .permitAll()
                )
                .requestCache(cache -> cache.requestCache(new HttpSessionRequestCache()))
                .csrf(AbstractHttpConfigurer::disable) //
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/exception/expire")
                ) //세션 만료 시 리디렉션
                .cors(cors -> cors.configurationSource(configurationSource()))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String redirectUrl = getRedirectUrl(request);
                            System.out.println(redirectUrl);
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

    private String getRedirectUrl(HttpServletRequest request) {
        String host = request.getHeader("Host");
        String referer = request.getHeader("Referer");
        return referer.substring(referer.lastIndexOf(host) + host.length());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    private String[] permitAllUrlPatterns() {
        // /meeting/1
        return new String[]{"/*", "/static/**" ,"/css/**", "/api/**", "/meeting/*", "/js/**", "/posts/**", "/imgs/**", "/users/join", "/users/**", "/home", "/exception/expire"};
    } // *, /users/1, /users/**/ n

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;

    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
