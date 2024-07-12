package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomPermissionDto {
    private Long userId; // 로그인된 사용자 id
    private String userName; // name
    private Long roomId; // room
    private List<ChatMessageDto> chatMessages;
}
