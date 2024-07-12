
package org.kosa.project.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.user.UserDto;
import org.kosa.project.service.dto.user.UserProfileDto;
import org.kosa.project.service.dto.user.UserRegisterForm;

@Mapper
public interface UserMapper {
    void insertUser(UserRegisterForm userDto);
    UserProfileDto findUserProfileById(Long userId);
    UserDto findUserByEmail(String email);
}

