package org.kosa.project.repository.mapper;

import org.junit.jupiter.api.Test;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MeetingTest {

    @Autowired
    private MeetingService meetingService;

    @Test
    public void detailMeeting()  {
        List<UserMeetingDto> userMeetingDto =  meetingService.attendanceList(22);
        System.out.println("테스트 중 : ========"+userMeetingDto.toString());
    }
}
