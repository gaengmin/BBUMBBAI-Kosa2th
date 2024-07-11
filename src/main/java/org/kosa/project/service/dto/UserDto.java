package org.kosa.project.service.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String phoneNumber;
    private String name;
    private String password;
    private LocalDate birth;
    private String myselfMemo;
    private LocalDate regDate;
}
