package org.kosa.project.repository;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.dto.search.Page;
import org.kosa.project.service.dto.search.Pageable;
import org.kosa.project.repository.mapper.UserMapper;
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
