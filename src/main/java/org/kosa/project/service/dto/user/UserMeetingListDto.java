package org.kosa.project.service.dto.user;

import lombok.*;
import org.kosa.project.service.Enum.MeetingStatus;
import org.kosa.project.service.Enum.UserMeetingStrategy;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMeetingListDto {
    private long meetingId;
    private String Subject; //미팅 제목
    private MeetingStatus meetingStatus; //미팅 상태
    private String userName; //만든 사람
    private UserMeetingStrategy userType; //미팅 타입
    private Date regDate;
}
