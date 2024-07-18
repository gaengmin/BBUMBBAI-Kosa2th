package org.kosa.project.controller;

import org.kosa.project.config.annotation.UserFileServiceQualifier;
import org.kosa.project.service.UserMeetingListService;
import org.kosa.project.service.dto.search.Page;
import org.kosa.project.service.dto.user.UserMeetingListDto;
import org.kosa.project.service.fileupload.FileUploadService;
import org.kosa.project.service.UserService;
import org.kosa.project.service.dto.user.UserProfileDto;
import org.kosa.project.service.dto.user.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FileUploadService fileUploadService;
    private final UserMeetingListService userMeetingListService;

    public UserController(UserService userService, @UserFileServiceQualifier FileUploadService fileUploadService, UserMeetingListService userMeetingListService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
        this.userMeetingListService = userMeetingListService;
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute @Validated UserRegisterForm userRegisterForm, BindingResult bindingResult, Model model) {
        System.out.println(userRegisterForm);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("userRegisterForm", userRegisterForm);
            return "joinForm";
        }

        //1. 파일을 저장
        MultipartFile imgFile = userRegisterForm.getProfileImg();
        String profileImageUrl = fileUploadService.saveFile(imgFile);

        // 2. 유저 정보를 저장.
        userRegisterForm.setProfileImgUrl(profileImageUrl);
        userService.save(userRegisterForm);

        // 3. 리다이렉트 한다.
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String userProfile1(@PathVariable Long userId,  String userType, Model model,
                               @RequestParam(defaultValue = "1") Integer page) {
        UserProfileDto userProfileDto = userService.getProfile(userId);
        Page<UserMeetingListDto> userMeetingJoinList = userMeetingListService.userMeetingJoinList(userId, userType, page, 5);
        model.addAttribute("userProfileDto", userProfileDto);
        model.addAttribute("joinList", userMeetingJoinList);
        model.addAttribute("userType", userType);
        return "profile";
    }


    @GetMapping("/profile/{email}")
    public String userProfile2(@PathVariable("email") String email, Model model) {
//        UserProfileDto userProfileDto = userService.getProfile(email);
//        model.addAttribute("userProfileDto",userProfileDto);
        return "userProfileDto";
    }



}

