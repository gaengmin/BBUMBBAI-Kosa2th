package org.kosa.project.controller;

import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.UserMeetingType;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/meeting")
public class MeetingRestController {

    private final MeetingService meetingService;

    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/detailMeeting")
    public ResponseEntity<?> userTypeMappingAction(
            @RequestBody UserMeetingCheckDto userMeetingCheckDto, //body -> model
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        System.out.println(userMeetingCheckDto);
        // 비로그인이면
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");
        }
        // 요청 본문에서 파라미터를 추출합니다.

        // setter
        userMeetingCheckDto.setUserId(Long.parseLong(userDetails.getUserId()));
        UserMeetingType userType = userMeetingCheckDto.getUserType();
        userType.handleAction(meetingService, userMeetingCheckDto);

        return ResponseEntity.ok().build();
    }
}
