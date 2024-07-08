package org.kosa.project.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MeetingMapper {
    void save(MeetingRegisterDto meetingDto);

    List<MeetingDetailDto> meetingList(int page, int pageSize);

    MeetingDetailDto detailMeeting(long meetingId);

    List<UserMeetingDto> attendanceList(long userMeetingId);
    
    int countMeetings();

}
