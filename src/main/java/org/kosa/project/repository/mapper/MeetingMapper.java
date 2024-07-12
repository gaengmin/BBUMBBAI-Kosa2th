package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.SearchConditionDto;
import org.kosa.project.service.dto.UserMeetingCheckDto;

@Mapper
public interface MeetingMapper {
    void save(MeetingRegisterDto meetingDto);

    Page<MeetingDetailDto> meetingList(@Param("condition") SearchConditionDto searchConditionDto, @Param("pageable") Pageable pageable);

    public long selectLastInsertId(long userId);

    void userMeetingSave(UserMeetingCheckDto userMeetingDto);

    MeetingDetailDto meetingDetails(long meetingId);


    int countMeetings(); //전체게시글

    /*user_meeting 관련*/
    String getUserMeetingCheck(long meetingId, long userId);

    /*모임참석 눌렀을시 현재원 수 업데이트*/
    void meetingUpdatePresentCount(long meetingId);

    /*모임나가기
     */
    void exitMeeting(UserMeetingCheckDto userMeetingDto);

}