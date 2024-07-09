package org.kosa.project.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMeetingDto {
    private long userMeetingId;
    private long userId;
    private String userName;
    private long meetingId;



}
