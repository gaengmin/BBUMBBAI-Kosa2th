package org.kosa.project.service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserRegisterForm {

    @Email(message = "이메일 양식을 입력해주세요.")
    private String email;

    @Length(min = 7, max = 30, message = "비밀번호는 최소 8자, 최대 30자까지 입력 가능합니다.")
    private String password;

    private String userName;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "[0-9]{3,4}-[0-9]{3,4}-[0-9]{3,4}", message = "올바른 전화번호 패턴이 아닙니다.")
    private String phoneNumber;

    private String introduce;
    private LocalDate birthDate;
    private String profileImgUrl;
    private MultipartFile profileImg;

}

