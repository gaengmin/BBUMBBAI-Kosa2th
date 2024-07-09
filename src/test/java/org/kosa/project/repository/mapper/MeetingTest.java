package org.kosa.project.repository.mapper;

import org.junit.jupiter.api.Test;
import org.kosa.project.controller.MeetingRegisterRequest;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MeetingTest {

    @Autowired
    private MeetingService meetingService;

    @Test
    public void save() {

    }
/*    @Test
    public void detailMeeting()  {
        MeetingDetailDto meetingDetailDto = meetingService.meetingDetails(22);
        System.out.println("테스트 중 : ========"+meetingDetailDto.getCategory());
        for(int i = 0; i<meetingDetailDto.getUserMeetingDto().size(); i++){
            System.out.println(meetingDetailDto.getUserMeetingDto().get(i).getUserName());
        }*/
}


    /*@Test
    public void result() {
        String result = meetingService.getUserMeetingCheck(22, 22);
        System.out.println(result);
    }*/
   /* @Test
    public void detailMeeting()  {
        List<UserMeetingDto> userMeetingDto =  meetingService.attendanceList(22);
        System.out.println("테스트 중 : ========"+userMeetingDto.toString());
    }
/*
    @Test
    public void save() {
        UserMeetingDto userMeetingDto = new UserMeetingDto();
              userMeetingDto.setMeetingId(22);
                userMeetingDto.setUserId(22);
                userMeetingDto.setUserType("모임원");
        meetingService.attendanceSave(userMeetingDto);
        meetingService.attendanceList(22);
    }*/

