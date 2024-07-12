package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.Enum.UserMeetingStrategy;
import org.kosa.project.service.dto.meeting.MeetingDetailDto;
import org.kosa.project.service.dto.meeting.MeetingRegisterDto;
import org.kosa.project.service.dto.meeting.MeetingSummaryDto;
import org.kosa.project.service.dto.search.SearchConditionDto;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
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

        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto(userId, UserMeetingStrategy.LEADER, meetingId);
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
        UserMeetingStrategy nowUserType = userMeetingCheckDto.getUserType();
        long meetingId = userMeetingCheckDto.getMeetingId();

        if (nowUserType.equals(UserMeetingStrategy.NOT_FOLLOWER)) {
            userMeetingCheckDto.setUserType(UserMeetingStrategy.WAIT);
            meetingRepository.userMeetingSave(userMeetingCheckDto);

        } else if (nowUserType.equals(UserMeetingStrategy.WAIT)) {
            userMeetingCheckDto.setUserType(UserMeetingStrategy.FOLLOWER);
            meetingRepository.userMeetingUpdate(userMeetingCheckDto);
            meetingRepository.meetingUpdateCountAndStatus(meetingId);
        }
    }

    @Transactional
    /*모임 나가기 버튼  현재원 수 업데이트*/
    public void exitMeetingService(UserMeetingCheckDto userMeetingCheckDto) {
        UserMeetingStrategy nowUserType = userMeetingCheckDto.getUserType();
        long meetingId = userMeetingCheckDto.getMeetingId();

        if (nowUserType.equals(UserMeetingStrategy.WAIT)) {
            meetingRepository.exitMeeting(userMeetingCheckDto);

        } else if (nowUserType.equals(UserMeetingStrategy.FOLLOWER)) {

            meetingRepository.exitMeeting(userMeetingCheckDto);
            meetingRepository.meetingUpdateCountAndStatus(userMeetingCheckDto.getMeetingId());
        }

    }
}
