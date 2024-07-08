package org.kosa.project.service.dto;

import lombok.*;

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
    private String userType;

}
