package org.kosa.project.repository;

import lombok.RequiredArgsConstructor;
import org.kosa.project.controller.MeetingController;
import org.kosa.project.repository.mapper.MeetingMapper;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.SearchCondition;
import org.springframework.stereotype.Repository;

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

    public MeetingDetailDto detailMeeting(long meetingId){

        return meetingMapper.detailMeeting(meetingId);
    }

    public int countMeetings() {

        return meetingMapper.countMeetings();
    }
}
