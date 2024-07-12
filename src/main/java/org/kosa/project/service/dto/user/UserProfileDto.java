package org.kosa.project.service.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDto {
    private Long userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private String profileImgUrl; //user_id -> DB, userId
    private String introduction;

    @Override
    public String toString() {
        return "UserProfileDto{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

}
