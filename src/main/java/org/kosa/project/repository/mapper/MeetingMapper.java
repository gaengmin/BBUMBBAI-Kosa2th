package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.service.dto.RoomPermissionDto;
import org.kosa.project.service.dto.meeting.MeetingDetailDto;
import org.kosa.project.service.dto.meeting.MeetingRegisterDto;
import org.kosa.project.service.dto.meeting.MeetingSummaryDto;
import org.kosa.project.service.dto.search.SearchConditionDto;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
import org.kosa.project.service.dto.user.UserMeetingDto;


@Mapper
public interface MeetingMapper {
    void save(MeetingRegisterDto meetingDto);


    Page<MeetingSummaryDto> meetingList(@Param("condition") SearchConditionDto searchConditionDto, @Param("pageable") Pageable pageable);

    long selectLastInsertId(long userId);

    void userMeetingSave(UserMeetingCheckDto userMeetingDto);

    MeetingDetailDto meetingDetails(long meetingId);

    int countMeetings(); //전체게시글
    void userMeetingUpdate(UserMeetingCheckDto userMeetingCheckDto);

    /*user_meeting 관련*/
    String getUserMeetingCheck(long meetingId, long userId);

    /*모임참석 눌렀을시 현재원 수 업데이트*/
    void meetingUpdateCountAndStatus(long meetingId);

    /*모임나가기*/
    void exitMeeting(UserMeetingCheckDto userMeetingDto);

    UserMeetingDto findUserMeetingByUserIdAndMeetingId(@Param("meetingId") Long meetingId, @Param("userId") Long userId);

    RoomPermissionDto findRoomWithAllChatListByMeetingAndUser(@Param("meetingId") Long meetingId, @Param("userId") Long userId);
}