package org.kosa.project.service.exception.meeting;

public class MeetingUserNotLoginException extends MeetingUserException {

    public MeetingUserNotLoginException(String message) {
        super("로그인이 필요합니다.");
    }
}
