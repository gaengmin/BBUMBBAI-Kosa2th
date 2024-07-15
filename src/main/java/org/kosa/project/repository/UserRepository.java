package org.kosa.project.repository;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.mapper.UserMapper;
import org.kosa.project.service.dto.user.UserMeetingDto;
import org.kosa.project.service.dto.user.UserMeetingListDto;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserMapper userMapper;

    public Page<UserMeetingListDto> userMeetingJoinList (long userId, String userType, Pageable pageable){
        return userMapper.userMeetingJoinList(userId,userType, pageable);
    }
}
