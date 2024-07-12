package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long reMeetingId;
    private Long meetingId;
    private Long userId;
    private String userName;
    private String content;
    private String regDate;
}
