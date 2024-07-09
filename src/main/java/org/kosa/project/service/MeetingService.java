package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {
    private final MeetingRepository meetingRepository;

    @Transactional
    public void save(MeetingRegisterDto meetingDto) {
        meetingRepository.save(meetingDto);
    }

    public List<MeetingDetailDto> meetingList(int page, int pageSize) {

        return meetingRepository.meetingList(page, pageSize);
    }


    public int getTotalMeetingCount() {

        return meetingRepository.countMeetings();
    }

    public void attendanceSave(UserMeetingDto userMeetingDto) {
        meetingRepository.attendanceSave(userMeetingDto);
    }

    public String getUserMeetingCheck(long userId, long meetingId) {
        return meetingRepository.getUserMeetingCheck(userId, meetingId);
    }

    public MeetingDetailDto meetingDetails(long meetingId) {

        return meetingRepository.meetingDetails(meetingId);
    }

}
