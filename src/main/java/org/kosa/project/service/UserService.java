package org.kosa.project.service;

import org.kosa.project.repository.mapper.UserMapper;
import org.kosa.project.service.dto.UserProfileDto;
import org.kosa.project.service.dto.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void save(UserRegisterForm userRegisterForm) {
        userMapper.insertUser(userRegisterForm);
    }

    public UserProfileDto getProfile(Long userId) {
        return userMapper.findUserProfileById(userId);
    }
}
