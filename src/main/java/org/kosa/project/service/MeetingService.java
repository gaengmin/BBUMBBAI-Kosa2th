package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.repository.MeetingRepository;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public void save(MeetingRegisterDto meetingDto, String fileUploadUrl) {
        meetingDto.setFileUploadUrl(fileUploadUrl);
        meetingRepository.save(meetingDto);
    }

    public List<MeetingDetailDto> meetingList(int startRow, int endRow) {

        return meetingRepository.meetingList(startRow, endRow);
    }

    public MeetingDetailDto detailMeeting(long meetingId) {

        return meetingRepository.detailMeeting(meetingId);
    }

    public int getTotalMeetingCount(){

        return meetingRepository.countMeetings();
    }
}