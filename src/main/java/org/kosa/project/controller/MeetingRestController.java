package org.kosa.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.UserMeetingStrategy;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
import org.kosa.project.service.dto.RoomPermissionDto;
import org.kosa.project.service.dto.SaveChatMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meeting")
@Slf4j
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
    /**
     * netty 접속을 위한 roomId, userId를 리턴
     */
    @GetMapping("/{meetingId}/rooms")
    public ResponseEntity<RoomPermissionDto> issueRoomPermission(@PathVariable Long meetingId,
                                                                 @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            log.error("Access denied For Not Authenticated User");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Long userId = userDetails.getUserId();
        return ResponseEntity.ok().body(meetingService.issueRoomPermission(meetingId, userId));
    }

    @PostMapping("/{meetingId}/chat")
    public String saveChatMessage(@RequestBody SaveChatMessageDto chatMessage, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return null;
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
