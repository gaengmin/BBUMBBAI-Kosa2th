package org.kosa.project.service;

import lombok.RequiredArgsConstructor;
import org.kosa.project.repository.mapper.MeetingMapper;
import org.kosa.project.service.dto.SaveChatMessageDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final MeetingMapper meetingMapper;

    public Long saveChat(SaveChatMessageDto chatMessage) {
        return meetingMapper.saveChat(chatMessage);
    }
}
