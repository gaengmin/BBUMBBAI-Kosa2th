package org.kosa.project.repository;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.MeetingMapper;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.SearchCondition;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepository {
    private final MeetingMapper meetingMapper;

    public void save(MeetingRegisterDto meetingDto) {
        meetingMapper.save(meetingDto);
    }


    public List<MeetingDetailDto> meetingList(SearchCondition searchCondition, int pageSize) {
        return meetingMapper.meetingList(searchCondition, pageSize);
    }


    public int countMeetings() {
        return meetingMapper.countMeetings();
    }

    /*모임참여관련 */

    public void attendanceSave(UserMeetingDto userMeetingDto) {

        meetingMapper.attendanceSave(userMeetingDto);
    }

    public String getUserMeetingCheck(long userId, long meetingId) {

        return meetingMapper.getUserMeetingCheck(userId, meetingId);
    }

    public MeetingDetailDto meetingDetails(long meetingId) {
        return meetingMapper.meetingDetails(meetingId);
    }


}
