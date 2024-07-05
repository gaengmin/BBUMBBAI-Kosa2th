package org.kosa.project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMeetingDto {
    private long userMeetingId;
    private long userId;
    private long meetingId;
    private String role;
}
