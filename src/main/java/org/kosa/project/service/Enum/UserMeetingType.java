package org.kosa.project.service.Enum;

import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;
import org.springframework.security.authentication.InsufficientAuthenticationException;

public enum UserMeetingType {
    LEADER("모임장") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
            // event 발생 예정
        }
    },
    FOLLOWER("모임원") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
            meetingService.exitMeetingService(checkDto);
        }
    },
    NOT_FOLLOWER("미참여") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
            checkDto.setUserType(FOLLOWER);
            meetingService.meetingUserAttend(checkDto);
        }
    },
    NOT_LOGIN("미인증") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
            throw new MeetingUserNotLoginException("로그인 필요");
        }
    };

    private final String userTypeName;

    UserMeetingType(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public abstract void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto);

//    {
//        UserMeetingType userMeetingType = this;
//        switch (userMeetingType) {
//            case LEADER :
//                // LEADER action 처리
//                break;
//            case FOLLOWER:
//                meetingService.exitMeetingService(checkDto);
//                break;
//            case NOT_FOLLOWER:
//                meetingService.meetingUserAttend(checkDto);
//                break;
//            case NOT_LOGIN:
//                throw new InsufficientAuthenticationException("로그인해주세요.");
//        }
//
//    }
}
