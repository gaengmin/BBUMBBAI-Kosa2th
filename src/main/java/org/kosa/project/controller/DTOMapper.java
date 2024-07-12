package org.kosa.project.controller;

import lombok.experimental.UtilityClass;
import org.kosa.project.service.Enum.MeetingStatus;
import org.kosa.project.service.dto.MeetingRegisterDto;

import java.time.LocalDateTime;
import java.util.Date;

@UtilityClass
public class DTOMapper{
    static MeetingRegisterDto convertToMeetingRegisterDto(MeetingRegisterRequest request, String convertedUrl, Long userId) {
        //userId로 받아온 건 request가 아니라 파라미터로 받기
        return new MeetingRegisterDto(0, userId, request.category(), request.subject(), request.context(), request.totalMember(), convertedUrl, MeetingStatus.CONTINUE, request.deadLineTime());
    }
}