package org.kosa.project.service.dto;

import lombok.*;
import org.kosa.project.service.Enum.UserMeetingType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserMeetingCheckDto {
    private long userId;
    private UserMeetingType userType;
    private long meetingId;
    private int confirmCheck; // 0이면 나가기, 1이면 등록

    public UserMeetingCheckDto(long userId, UserMeetingType userType, long meetingId) {
        this.userId = userId;
        this.userType = userType;
        this.meetingId = meetingId;
    }
}
