package org.kosa.project.service.dto.meeting;

import lombok.*;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.MeetingStatus;
import org.kosa.project.service.dto.user.UserMeetingDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MeetingDetailDto {
    private long meetingId;
    private long userId;
    private long regionId;
    private Category category; // ENUM
    private String subject;
    private String context;
    private int totalMember;
    private int presentMember;
    private String fileName;
    private MeetingStatus meetingStatus;
    private LocalDateTime deadLineTime;
    private LocalDate regDate;
    private double latitude;
    private double longitude;
    private List<UserMeetingDto> userMeetingDto;
}
