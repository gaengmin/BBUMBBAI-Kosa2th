package org.kosa.project.service.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long meetingCommentId;
    private Long meetingId;
    private Long userId;
    private String userName;
    private String content;
    private String regDate;
}
