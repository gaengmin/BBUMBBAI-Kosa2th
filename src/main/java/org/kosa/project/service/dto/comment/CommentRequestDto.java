package org.kosa.project.service.dto.comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentRequestDto {
    private Long meetingCommentId;
    private Long meetingId;
    private Long userId;
    private String content;
}
