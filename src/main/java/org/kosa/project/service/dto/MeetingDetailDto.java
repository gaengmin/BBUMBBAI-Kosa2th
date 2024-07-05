package org.kosa.project.service.dto;

import lombok.*;
import org.kosa.project.service.Enum.Category;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MeetingDetailDto {
    private long meetingId;
    private long userId;
    private long regionId;
    private Category category; // ENUM
    private String subject;
    private String context;
    private int totalMember;
    private int presentMember;
    private String fileName;
    private String meetingStatus;
    private LocalDate regDate;
}
