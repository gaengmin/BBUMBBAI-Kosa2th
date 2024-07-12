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
        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto();
        userMeetingCheckDto.setMeetingId(meetingId);
        userMeetingCheckDto.setUserId(userId);
        userMeetingCheckDto.setUserType(UserMeetingType.LEADER);
        System.out.println(userMeetingCheckDto.getMeetingId() + "     ====     "+ userMeetingCheckDto.getUserId()+ "     ===" + userMeetingCheckDto.getUserType().getUserTypeName() );
        meetingRepository.userMeetingSave(userMeetingCheckDto);
    }

    public Page<MeetingDetailDto> meetingList(SearchConditionDto searchConditionDto, int page, int pageSize) {
        return meetingRepository.meetingList(searchConditionDto, new Pageable(page, pageSize));
    }

    public MeetingDetailDto meetingDetails(long meetingId) {
        return meetingRepository.meetingDetails(meetingId);
    }

    @Transactional
    /*모임참석 눌렀을시 모임 참석 후현재원 수 업데이트*/
    public void meetingUserAttend(UserMeetingCheckDto userMeetingCheckDto) {
        meetingRepository.userMeetingSave(userMeetingCheckDto);
        meetingRepository.meetingUpdatePresentCount(userMeetingCheckDto.getMeetingId());
    }

    @Transactional
    /*모임 나가기 버튼  현재원 수 업데이트*/
    public void exitMeetingService(UserMeetingCheckDto userMeetingCheckDto) {
        meetingRepository.exitMeeting(userMeetingCheckDto);
        meetingRepository.meetingUpdatePresentCount(userMeetingCheckDto.getMeetingId());
    }
}
