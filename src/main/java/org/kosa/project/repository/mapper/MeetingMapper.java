package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.SearchCondition;
import org.kosa.project.service.dto.UserMeetingDto;


import java.util.List;

@Mapper
public interface MeetingMapper {
    void save(MeetingRegisterDto meetingDto);

    List<MeetingDetailDto> meetingList(@Param("condition") SearchCondition searchCondition, @Param("pageSize") int pageSize);

    int countMeetings(); //전체게시글

    /*user_meeting 관련*/
    void attendanceSave(UserMeetingDto userMeetingDto);

    String getUserMeetingCheck(long meetingId, long userId);

    MeetingDetailDto meetingDetails (long meetingId);
}
