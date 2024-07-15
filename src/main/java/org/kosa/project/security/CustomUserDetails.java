package org.kosa.project.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@ToString
public class CustomUserDetails implements UserDetails {
    //TODO: long으로 바꾸기
    private static final String USER_AUTHORITY = "ROLE_USER";
    private final String userName;
    private final long userId;
    private final String email;
    private final String password;

    public CustomUserDetails(String userName, long userId, String email, String password) {
        this.userName = userName;
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(USER_AUTHORITY));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
