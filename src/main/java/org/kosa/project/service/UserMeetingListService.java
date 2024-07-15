package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.UserRepository;
import org.kosa.project.service.dto.user.UserMeetingListDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMeetingListService {

    private final UserRepository userRepository;

    public Page<UserMeetingListDto> userMeetingJoinList (long userId, String userType, int page, int pageSize){
        return userRepository.userMeetingJoinList(userId, userType, new Pageable(page, pageSize));
    }
}
