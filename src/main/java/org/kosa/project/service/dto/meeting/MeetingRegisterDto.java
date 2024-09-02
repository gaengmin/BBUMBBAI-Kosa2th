package org.kosa.project.service.dto.meeting;

import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.MeetingStatus;

import java.time.LocalDateTime;

public record MeetingRegisterDto(long meetingId, long userId, Category category, String subject,
                                 String context, int totalMember, String fileUploadUrl, MeetingStatus status,
                                 LocalDateTime deadLineTime, Double latitude, Double longitude) {

}
