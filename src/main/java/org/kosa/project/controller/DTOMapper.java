package org.kosa.project.controller;

import lombok.experimental.UtilityClass;
import org.kosa.project.service.dto.MeetingRegisterDto;

@UtilityClass
public class DTOMapper{
    static MeetingRegisterDto convertToMeetingRegisterDto(MeetingRegisterRequest request, String convertedUrl) {
        return new MeetingRegisterDto(request.meetingId(), request.userId(), request.regionId(), request.category(), request.subject(), request.context(), request.totalMember(), convertedUrl, request.meetingStatus());
    }
}
