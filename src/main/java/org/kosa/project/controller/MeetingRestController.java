package org.kosa.project.controller;

import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.UserMeetingType;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        // 비로그인이면
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");
        }
        // 요청 본문에서 파라미터를 추출합니다.
        long meetingId = Long.parseLong(payload.get("meetingId").toString());
        UserMeetingType userType = UserMeetingType.valueOf(payload.get("action").toString());

        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto();
        userMeetingCheckDto.setMeetingId(meetingId);
        userMeetingCheckDto.setUserId(Long.parseLong(userDetails.getUserId()));
        userMeetingCheckDto.setUserType(userType);

        userType.handleAction(meetingService, userMeetingCheckDto);

        return ResponseEntity.ok().build();
    }
}
