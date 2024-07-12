package org.kosa.project.service.exception.meeting;

public class MeetingUserNotSufficientException extends MeetingUserException {

    public MeetingUserNotSufficientException() {
        this("해당 기능을 사용할 수 없는 사용자입니다.");
    }

    public MeetingUserNotSufficientException(String message) {
        super(message);
    }
}
