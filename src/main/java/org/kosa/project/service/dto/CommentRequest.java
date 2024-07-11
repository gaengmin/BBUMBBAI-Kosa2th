package org.kosa.project.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
    private long reMeetingId;
    private long meetingId;
    private long userId;
    private String comment;
}
