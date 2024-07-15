package org.kosa.project.service.dto.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.kosa.project.service.Enum.Category;

@Getter
@Setter
@ToString
public class SearchDetailDto {
    private String fileName;
    private Category category;
    private String subject;
    private int totalMember;
    private int presentMember;
    private long meetingId;
}
