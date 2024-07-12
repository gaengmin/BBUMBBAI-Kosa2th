package org.kosa.project.service.dto.comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CommentRequestDto {
    private Long reMeetingId;
    private Long meetingId;
    private Long userId;
    private String content;
}
