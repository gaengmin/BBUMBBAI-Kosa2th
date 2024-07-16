package org.kosa.project.service.dto.user;

import lombok.*;
import org.kosa.project.service.Enum.UserMeetingStrategy;

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
    private UserMeetingStrategy userType;// 회원 정보
}
