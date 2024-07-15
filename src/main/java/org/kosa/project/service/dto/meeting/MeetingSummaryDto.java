package org.kosa.project.service.dto.meeting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.MeetingStatus;

@Getter
@Setter
@ToString
public class MeetingSummaryDto {
    private long meetingId;
    private long userId;
    private String userName;
    private Category category; // ENUM
    private String subject;
    private int totalMember;
    private int presentMember;
    private String fileName;
    private MeetingStatus meetingStatus;
}
