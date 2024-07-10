package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.kosa.project.service.Enum.Category;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCondition {
    private String content;
    private Category category;
    private Integer page;
    private String searchType;
}