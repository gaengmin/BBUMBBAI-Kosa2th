package org.kosa.project.service.dto;

import lombok.*;
import org.kosa.project.service.Enum.Category;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MeetingRegisterDto {

    private long meetingId;
    private long userId;
    private long regionId;

    private Category category; // ENUM

    private String subject;
    private String context;
    private int totalMember;
    private String fileUploadUrl;
    private MultipartFile image;
    private String meetingStatus;

    public void setCategory(String name) {
        for (Category c : Category.values()) {
            if (c.getDisplayName().equals(name)) {
                System.out.println(name);
                this.category = c;
            }
        }
    }

    public Category getCategory() {
        return category;
    }

}
