package org.kosa.project.service;

import org.kosa.project.repository.mapper.UserMapper;
import org.kosa.project.service.dto.user.UserProfileDto;
import org.kosa.project.service.dto.user.UserRegisterForm;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void save(UserRegisterForm userRegisterForm) {
        userMapper.insertUser(userRegisterForm);
    }


    public String findUserByEmailCheck(String email) {
        String userEmail = userMapper.findUserByEmailCheck(email);

        System.out.println("UserService: Email checked: " + email + ", Found: " + userEmail);  // 디버깅용 로그

        return userEmail;
    }

    public UserProfileDto getProfile(Long userId) {
        return userMapper.findUserProfileById(userId);
    }

}
