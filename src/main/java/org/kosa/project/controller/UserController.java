package org.kosa.project.controller;

import org.kosa.project.config.annotation.UserFileServiceQualifier;
import org.kosa.project.service.fileupload.FileUploadService;
import org.kosa.project.service.UserService;
import org.kosa.project.service.dto.UserProfileDto;
import org.kosa.project.service.dto.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FileUploadService fileUploadService;

    public UserController(UserService userService, @UserFileServiceQualifier FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserRegisterForm userRegisterForm) {
        System.out.println(userRegisterForm);
        // 1. 파일을 저장
        MultipartFile imgFile = userRegisterForm.getProfileImg();
        String profileImageUrl = fileUploadService.saveFile(imgFile);

        // 2. 유저 정보를 저장.
        userRegisterForm.setProfileImgUrl(profileImageUrl);
        userService.save(userRegisterForm);

        // 3. 리다이렉트 한다.
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String userProfile1(@PathVariable Long userId, Model model) {
        UserProfileDto userProfileDto = userService.getProfile(userId);
        System.out.println(userProfileDto);
        model.addAttribute("userProfileDto", userProfileDto);
        return "profile";
    }

    @GetMapping("/profile/{email}")
    public String userProfile2(@PathVariable("email") String email, Model model) {
//        UserProfileDto userProfileDto = userService.getProfile(email);
//        model.addAttribute("userProfileDto",userProfileDto);
        return "userProfileDto";
    }

}

