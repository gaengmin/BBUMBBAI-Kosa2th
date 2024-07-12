package org.kosa.project.service.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
public class UserDto {
    private final Long userId;
    private String phoneNumber;
    private final String userEmail;
    private final String name;
    private final String password;
    private LocalDate birth;
    private String myselfMemo;
    private LocalDate regDate;

    public UserDto(Long userId, String userEmail, String name, String password) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.name = name;
        this.password = password;
    }
}
