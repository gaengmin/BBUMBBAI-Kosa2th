package org.kosa.project.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.LoginForm;
import org.kosa.project.service.dto.UserDetailDto;
import org.kosa.project.service.dto.UserProfileDto;
import org.kosa.project.service.dto.UserRegisterForm;

@Mapper
public interface UserMapper {
    void insertUser(UserRegisterForm userDto);
    UserProfileDto findUserProfileById(Long userId);
    UserDetailDto findUserByEmail(String email);
}
