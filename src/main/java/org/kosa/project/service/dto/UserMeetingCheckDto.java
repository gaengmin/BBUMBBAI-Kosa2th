package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.kosa.project.service.Enum.UserType;

@Getter
@Setter
public class UserMeetingCheckDto {
    private long userId;
    private String userType;
    private long meetingId;
}
