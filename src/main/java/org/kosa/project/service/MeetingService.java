package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.controller.MeetingController;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public void save(MeetingRegisterDto meetingDto) {
        System.out.println("fileUploadUrl :"+meetingDto.fileUploadUrl());
        meetingRepository.save(meetingDto);
    }

    public List<MeetingDetailDto> meetingList(int page, int pageSize) {

        return meetingRepository.meetingList(page, pageSize);
    }

    public MeetingDetailDto detailMeeting(long meetingId) {

        return meetingRepository.detailMeeting(meetingId);
    }

    public int getTotalMeetingCount(){

        return meetingRepository.countMeetings();
    }

    public List<UserMeetingDto> attendanceList(long meetingId) {

        return meetingRepository.attendanceList(meetingId);
    }
}
