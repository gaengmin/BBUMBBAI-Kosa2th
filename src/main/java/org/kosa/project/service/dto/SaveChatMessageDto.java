package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class SaveChatMessageDto {
    private Long chatId;
    private Long roomId;
    private Long userId;
    private String userName;
    private String message;
    private LocalDateTime sendDateTime;
}
