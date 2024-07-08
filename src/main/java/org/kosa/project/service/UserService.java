package org.kosa.project.service;

import org.kosa.project.repository.mapper.UserMapper;
import org.kosa.project.service.dto.UserProfileDto;
import org.kosa.project.service.dto.UserRegisterForm;
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

    public UserProfileDto getProfile(Long userId) {
        return userMapper.findUserProfileById(userId);
    }
}
