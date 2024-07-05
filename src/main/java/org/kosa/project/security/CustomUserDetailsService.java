package org.kosa.project.security;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.UserMapper;
import org.kosa.project.service.dto.LoginForm;
import org.kosa.project.service.dto.UserDetailDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailDto userDetailDto = userMapper.findUserByEmail(email);
        if (userDetailDto == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return new CustomUserDetails(userDetailDto.getUserName(), userDetailDto.getUserId(), userDetailDto.getEmail(), bCryptPasswordEncoder.encode(userDetailDto.getPassword()));
    }
}





