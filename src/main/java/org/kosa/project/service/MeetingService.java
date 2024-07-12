package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.Enum.UserMeetingType;
import org.kosa.project.service.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {
    private final MeetingRepository meetingRepository;

    @Transactional
    public void save(MeetingRegisterDto meetingDto) {
        meetingRepository.save(meetingDto);

        long meetingId = meetingRepository.selectLastInsertId(meetingDto.userId());
        long userId = meetingDto.userId();

        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto(userId, UserMeetingType.LEADER, meetingId);
        meetingRepository.userMeetingSave(userMeetingCheckDto);
    }


    public Page<MeetingSummaryDto> meetingList(SearchConditionDto searchConditionDto, int page, int pageSize) {
        return meetingRepository.meetingList(searchConditionDto, new Pageable(page, pageSize));
    }

    public MeetingDetailDto meetingDetails(long meetingId) {
        return meetingRepository.meetingDetails(meetingId);
    }


    //모임 참석 시 권한에 따른
    @Transactional
    public void meetingUserAttend(UserMeetingCheckDto userMeetingCheckDto) {
        UserMeetingType nowUserType = userMeetingCheckDto.getUserType();
        long meetingId = userMeetingCheckDto.getMeetingId();

        if (nowUserType.equals(UserMeetingType.NOT_FOLLOWER)) {
            userMeetingCheckDto.setUserType(UserMeetingType.WAIT);
            meetingRepository.userMeetingSave(userMeetingCheckDto);

        } else if (nowUserType.equals(UserMeetingType.WAIT)) {
            userMeetingCheckDto.setUserType(UserMeetingType.FOLLOWER);
            meetingRepository.userMeetingUpdate(userMeetingCheckDto);
            meetingRepository.meetingUpdateCountAndStatus(meetingId);
        }
    }

    @Transactional
    /*모임 나가기 버튼  현재원 수 업데이트*/
    public void exitMeetingService(UserMeetingCheckDto userMeetingCheckDto) {
        UserMeetingType nowUserType = userMeetingCheckDto.getUserType();
        long meetingId = userMeetingCheckDto.getMeetingId();

        if (nowUserType.equals(UserMeetingType.WAIT)) {
            meetingRepository.exitMeeting(userMeetingCheckDto);

        } else if (nowUserType.equals(UserMeetingType.FOLLOWER)) {

            meetingRepository.exitMeeting(userMeetingCheckDto);
            meetingRepository.meetingUpdateCountAndStatus(userMeetingCheckDto.getMeetingId());
        }

    }
}
