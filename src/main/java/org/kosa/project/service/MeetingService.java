package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.Enum.UserMeetingType;
import org.kosa.project.service.dto.*;
import org.kosa.project.service.exception.meeting.MeetingUserNotSufficientException;
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

    public Page<MeetingSummaryDto> meetingList(SearchCondition searchCondition, int page, int pageSize) {
        return meetingRepository.meetingList(searchCondition, new Pageable(page, pageSize));
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

    public RoomPermissionDto issueRoomPermission(Long meetingId, Long userId) {
        // 해당 user가 참여하고 있는 meeting 인지의 여부를 확인 한다.
        UserMeetingDto userMeeting = meetingRepository.findUserMeetingByUserIdAndMeetingId(meetingId, userId);
        if (userMeeting == null || userMeeting.getUserType() == UserMeetingType.NOT_FOLLOWER) {
            // 해당 유저는 참여 권한이 없다. 해당 url로 리다이렉트 보내면 된다.
            throw new MeetingUserNotSufficientException();
        }
        // 참여 권한이 있는 유저일 경우, meetingId, userId를 통해 조회된 roomId와 주고 받은 모든 채팅 목록을 반환 받는다.
        // 1. meetingId로 roomId를 조회
        // 2. userId로 user_name등의 간단 정보를 조회
        // 3. roomId를 통한 전체 채팅 내역 조회
        return meetingRepository.findRoomWithAllChatListByMeetingAndUser(meetingId, userId);
    }
}
