package org.kosa.project.service.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRegisterForm {
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private String introduce;
    private LocalDate birthDate;
    private String profileImgUrl;
    private MultipartFile profileImg;
}

// imgs/users