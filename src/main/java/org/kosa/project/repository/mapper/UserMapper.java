
package org.kosa.project.repository.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.service.dto.user.UserDto;
import org.kosa.project.service.dto.user.UserMeetingListDto;
import org.kosa.project.service.dto.user.UserProfileDto;
import org.kosa.project.service.dto.user.UserRegisterForm;

@Mapper
public interface UserMapper {
    void insertUser(UserRegisterForm userDto);
    UserProfileDto findUserProfileById(Long userId);
    UserDto findUserByEmail(String email);

    String findUserByEmailCheck(String email);

    //내가 만든 모임 리스트
    Page<UserMeetingListDto> userMeetingJoinList (long userId, @Param("userType") String userType, @Param("pageable") Pageable pageable);
}

