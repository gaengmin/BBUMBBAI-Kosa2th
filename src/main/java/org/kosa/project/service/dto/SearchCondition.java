package org.kosa.project.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {
    private String content;
    private int category;
    private Integer page;
}

