package org.kosa.project.service.dto.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String phoneNumber;
    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String myselfMemo;
    private LocalDate regDate;

    public UserDto(Long userId, String email, String userName, String password) {
        this.userId = userId;
        this.email = email;
        this.name = userName;
        this.password = password;
    }
}
