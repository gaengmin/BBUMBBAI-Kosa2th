package org.kosa.project.controller;

import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.UserMeetingStrategy;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MeetingRestController {

    private final MeetingService meetingService;

    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/detailMeeting")
    public ResponseEntity<?> userTypeMappingAction(
            @RequestBody UserMeetingCheckDto userMeetingCheckDto, //body -> model
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        // 비로그인이면
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");
        }

        // setter
        userMeetingCheckDto.setUserId(userDetails.getUserId());
        UserMeetingStrategy userType = userMeetingCheckDto.getUserType();
        userType.handleAction(meetingService, userMeetingCheckDto);

        return ResponseEntity.ok().build();
    }

    /*관리자가 대기중인 회원 관리할 때.*/
    @PostMapping("/confirmCheck")
    public ResponseEntity<?> userConfirmCheckAction(
            @RequestBody UserMeetingCheckDto userMeetingCheckDto, //body -> model
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        // setter
        UserMeetingStrategy userType = userMeetingCheckDto.getUserType();
        userType.handleAction(meetingService, userMeetingCheckDto);

        return ResponseEntity.ok().build();
    }

}
