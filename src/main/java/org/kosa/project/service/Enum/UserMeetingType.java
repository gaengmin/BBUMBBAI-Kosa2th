package org.kosa.project.service.Enum;

import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;

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
    WAIT("대기중") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
            if (checkDto.getConfirmCheck() == 1 || checkDto.getConfirmCheck() ==2) {
                //권한자가 허용 및 내보낼 때.
                if(checkDto.getConfirmCheck() == 1){
                    meetingService.exitMeetingService(checkDto);
                }else{
                    meetingService.meetingUserAttend(checkDto);
                }
            }else {
                //이거는 일반 사람이
                meetingService.exitMeetingService(checkDto);
            }
        }
    },
    NOT_FOLLOWER("미참여") {
        @Override
        public void handleAction(MeetingService meetingService, UserMeetingCheckDto checkDto) {
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

}
