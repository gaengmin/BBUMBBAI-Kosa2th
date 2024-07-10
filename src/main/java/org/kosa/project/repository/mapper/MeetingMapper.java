package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.SearchCondition;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.kosa.project.service.dto.UserMeetingDto;

import java.util.List;

@Mapper
public interface MeetingMapper {
    void save(MeetingRegisterDto meetingDto);

    List<MeetingDetailDto> meetingList(@Param("condition") SearchCondition searchCondition, @Param("pageSize") int pageSize);

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