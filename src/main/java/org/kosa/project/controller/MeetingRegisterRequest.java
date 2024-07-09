package org.kosa.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.UserType;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public record MeetingRegisterRequest(Long userId, Category category, String subject,
                                     String context, Integer totalMember, MultipartFile image, String meetingStatus, UserType userType) {
    String validate() {
        if (category == null) {
            log.error("Category is null");
            return "redirect:/meeting/insertMeeting";
        }
        if (image.getContentType() == null || !image.getContentType().startsWith("image/")) {
            log.error("이미지 파일이 아님 {}", image.getContentType());
            return "redirect:uploadStatus";
        }
        return "redirect:/meeting/list";
    }
}
