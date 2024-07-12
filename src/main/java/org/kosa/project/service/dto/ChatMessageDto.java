package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ChatMessageDto {
    private Long meetingId;
    private Long writeUserId;
    private String writeUserName;
    private String message;
    private LocalDateTime writeTime;
}
