package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.Enum.UserMeetingStrategy;
import org.kosa.project.service.dto.RoomPermissionDto;
import org.kosa.project.service.dto.meeting.MeetingDetailDto;
import org.kosa.project.service.dto.meeting.MeetingRegisterDto;
import org.kosa.project.service.dto.meeting.MeetingSummaryDto;
import org.kosa.project.service.dto.search.SearchConditionDto;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
import org.kosa.project.service.dto.user.UserMeetingDto;
import org.kosa.project.service.exception.meeting.MeetingUserNotSufficientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {
    private final MeetingRepository meetingRepository;

    //게시물 생성 시 트랜잭션 처리
    @Transactional
    public void save(MeetingRegisterDto meetingDto) {
        meetingRepository.save(meetingDto);

        long meetingId = meetingRepository.selectLastInsertId(meetingDto.userId());
        long userId = meetingDto.userId();

        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto(userId, UserMeetingStrategy.LEADER, meetingId);
        meetingRepository.userMeetingSave(userMeetingCheckDto);
        meetingRepository.createMeetingRoom(meetingId);
    }

    /**
     * MEETING LIST
     */

    public Page<MeetingSummaryDto> meetingList(SearchConditionDto searchConditionDto, int page, int pageSize) {
        return meetingRepository.meetingList(searchConditionDto, new Pageable(page, pageSize));
    }

    /**
     *
     */
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

            /* 방장이 권한을 줄 떄*/
        } else if (nowUserType.equals(UserMeetingStrategy.WAIT)) {
            userMeetingCheckDto.setUserType(UserMeetingStrategy.FOLLOWER);
            meetingRepository.userMeetingUpdate(userMeetingCheckDto);
            meetingRepository.meetingUpdatePresentStatus(meetingId);
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
            meetingRepository.meetingUpdatePresentStatus(meetingId);
        }

    }

    public RoomPermissionDto issueRoomPermission(Long meetingId, Long userId) {
        // 해당 user가 참여하고 있는 meeting 인지의 여부를 확인 한다.
        UserMeetingDto userMeeting = meetingRepository.findUserMeetingByUserIdAndMeetingId(meetingId, userId);
        if (userMeeting == null || userMeeting.getUserType() == UserMeetingStrategy.NOT_FOLLOWER) {
            // 해당 유저는 참여 권한이 없다. 해당 url로 리다이렉트 보내면 된다.
            throw new MeetingUserNotSufficientException();
        }
        // 참여 권한이 있는 유저일 경우, meetingId, userId를 통해 조회된 roomId와 주고 받은 모든 채팅 목록을 반환 받는다.
        // 1. meetingId로 roomId를 조회
        // 2. userId로 user_name등의 간단 정보를 조회
        // 3. roomId를 통한 전체 채팅 내역 조회
        return meetingRepository.findRoomWithAllChatListByMeetingAndUser(meetingId, userId);
    }

    public void updateMeetingEndTime(){
        meetingRepository.updateMeetingEndTime();
    }
}
