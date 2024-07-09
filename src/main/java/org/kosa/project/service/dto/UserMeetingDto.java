package org.kosa.project.service.dto;

import lombok.*;
import org.kosa.project.service.Enum.UserType;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class UserMeetingDto {
    private long userMeetingId;
    private long userId;
    private String userName;
    private long meetingId;
    private UserType userType;
}
