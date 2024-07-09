package org.kosa.project.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

public class RequiredAuthorizationUrlMatcher {
    private List<AntPathRequestMatcher> requestMatchers = new ArrayList<>();

    public RequiredAuthorizationUrlMatcher(String... urlPatterns) {
        for (String pattern: urlPatterns) {
            requestMatchers.add(new AntPathRequestMatcher(pattern));
        }
    }

    public boolean matches(HttpServletRequest request) {
        for (AntPathRequestMatcher requestMatcher : requestMatchers) {
            if (!requestMatcher.matches(request)) {
                return false;
            }
        }
        return true;
    }
}
