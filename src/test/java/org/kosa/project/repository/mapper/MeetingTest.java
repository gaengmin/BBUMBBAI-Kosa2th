package org.kosa.project.repository.mapper;

import org.junit.jupiter.api.Test;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MeetingTest {

    @Autowired
    private MeetingService meetingService;

    @Test
    public void detailMeeting()  {
       MeetingDetailDto detailDto =  meetingService.detailMeeting(2);
        System.out.println(detailDto.toString());
    }
}
