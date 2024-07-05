package org.kosa.project.service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String phoneNumber; // 010-283-2
    private String name;
    private String password;
    private LocalDate birth; //
    private String myselfMemo;
    private LocalDate regDate;
}
