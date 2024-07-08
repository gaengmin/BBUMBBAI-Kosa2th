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
// meeting, user -> 가입, 권한 정보 확인해서 비즈니스 로직을 처리하는 부분
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public void save(MeetingRegisterDto meetingDto, String fileUploadUrl) {
        System.out.println("fileUploadUrl :"+fileUploadUrl);
        meetingDto.setFileUploadUrl(fileUploadUrl);
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
}
