package org.kosa.project.service.dto;

import lombok.*;
import org.kosa.project.service.Enum.UserMeetingType;

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
    private UserMeetingType userType; // 회원 정보에 대한 유저타입, 미팅참여에 대한 참여 유저 타입인지 모르겠다.
}
