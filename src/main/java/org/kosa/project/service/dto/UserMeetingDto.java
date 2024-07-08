package org.kosa.project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserMeetingDto {
    private long userMeetingId;
    private long userId;
    private long meetingId;
    private String role;

    public long getUserMeetingId() {
        return userMeetingId;
    }

    public void setUserMeetingId(long userMeetingId) {
        this.userMeetingId = userMeetingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
