package org.kosa.project.service.dto;

import org.kosa.project.service.Enum.Category;

public record MeetingRegisterDto(long meetingId, long userId, long regionId, Category category, String subject, String context, int totalMember, String fileUploadUrl, String meetingStatus) {
}
