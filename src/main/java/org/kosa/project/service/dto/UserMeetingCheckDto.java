package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.kosa.project.service.Enum.UserType;

@Getter
@Setter
public class UserMeetingCheckDto {
    private long userId;
    private UserType userType;
    private long meetingId;
}
