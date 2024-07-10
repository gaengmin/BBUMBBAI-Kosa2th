package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.kosa.project.service.Enum.UserMeetingType;

@Getter
@Setter
public class UserMeetingCheckDto {
    private long userId;
    private UserMeetingType userType;
    private long meetingId;
}